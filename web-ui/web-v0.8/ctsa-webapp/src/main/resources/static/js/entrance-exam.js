function getValues() {
    // Get slider values
    let parent = this.parentNode
    let slides = parent.getElementsByTagName("input")

    //value to show range later
    let divParent = this.parentNode.parentNode
    let inputValue = divParent.getElementsByTagName("input")

    let slide1 = parseFloat(slides[0].value)
    let slide2 = parseFloat(slides[1].value)

    // Neither slider will clip the other, so make sure we determine which is larger
    if (slide1 > slide2) {
        let tmp = slide2
        slide2 = slide1
        slide1 = tmp
    }
    let displayElement = document.getElementsByClassName("rangeValues")[2];
    inputValue[0].value = slide1 + " - " + slide2;
}

function retrieveSubjects(modalId) {
    let count = 0

    let modal = $("#" + modalId)

    let entranceExamSubjects = []

    axios
        .get(API_GATEWAY + RESULTS_SUGGESTIONS_SERVICE + "/entrance-exam-subjects")
        .then(response => {
            let subjects = response.data
            let subjectsPerCol = Math.ceil(subjects.length / 2)
            let col = $("<div>", {
                class: "col-md-6 pl-4"
            })

            subjects.forEach(subject => {
                entranceExamSubjects.push(subject)

                count++

                createSubjectInput(subject).appendTo(col)

                if (count % subjectsPerCol === 0) {
                    col.appendTo(modal)
                    col = $("<div>", {
                        class: "col-md-6 pl-4"
                    })
                }
            })
        })
        .then(() => {
            sessionStorage.setItem("entranceExamSubjects", JSON.stringify(entranceExamSubjects))
            // Initialize Sliders
            let sliderSections = document.getElementsByClassName("range-slider");
            for (let x = 0; x < sliderSections.length; x++) {
                let sliders = sliderSections[x].getElementsByTagName("input")
                for (let y = 0; y < sliders.length; y++) {
                    if (sliders[y].type === "range") {
                        sliders[y].oninput = getValues
                        // Manually trigger event first time to display values
                        sliders[y].oninput()
                    }
                }
            }
        })
}

function createSubjectInput(subject) {
    let subjectDiv = $("<div>", {
        id: subject.name + "Mark",
        class: "college-score-input"
    })

    $("<span>", {
        text: subject.vietnamese + " "
    }).appendTo(subjectDiv)

    $("<input>", {
        class: "rangeValues"
    }).appendTo(subjectDiv)

    if (!subject.required) {
        let label = $("<label>", {
            class: "float-right"
        })

        $("<input>", {
            type: "checkbox",
            value: "",
            onchange: "disableScoreInput(this)"
        }).appendTo(label)
        label.append("  Không thi")

        label.appendTo(subjectDiv)
    }

    let section = $("<section>", {
        style: "padding-bottom:20px",
        class: "range-slider"
    })

    $("<input>", {
        value: "5",
        min: "0",
        max: "10",
        step: "0.1",
        type: "range"
    }).appendTo(section)

    $("<input>", {
        value: "10",
        min: "0",
        max: "10",
        step: "0.1",
        type: "range"
    }).appendTo(section)

    section.appendTo(subjectDiv)

    return subjectDiv
}

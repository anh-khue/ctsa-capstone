function getValues() {
    // Get slider values
    let parent = this.parentNode
    let slides = parent.getElementsByTagName("input")

    //value to show range later
    let divParent = this.parentNode.parentNode
    let inputValue = divParent.getElementsByClassName("rangeValues")

    let slide1 = parseFloat(slides[0].value)
    let slide2 = parseFloat(slides[1].value)

    // Neither slider will clip the other, so make sure we determine which is larger
    if (slide1 > slide2) {
        let tmp = slide2
        slide2 = slide1
        slide1 = tmp
    }
    inputValue[0].value = slide1 + " - " + slide2;
}

function retrieveSubjects(modalId) {
    let count = 0

    let modal = $("#" + modalId)

    let entranceExamSubjects = []

    axios
        .get(API_GATEWAY + CAREERS_SUGGESTIONS_SERVICE + "/entrance-exam-subjects")
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
        .then(() => {
            let checkboxes = $("input:checkbox")
            for (let i = 0; i < checkboxes.length; i++) {
                disableScoreInput(checkboxes[i])
            }
        })
}

function createSubjectInput(subject) {
    let subjectDiv = $("<div>", {
        id: subject.name + "Mark",
        class: "college-score-input"
    })

    if (!subject.required) {
        let label = $("<label>", {
            class: "float-right"
        })

        $("<input>", {
            type: "checkbox",
            checked: "true",
            value: "",
            onchange: "disableScoreInput(this)"
        }).appendTo(label)
        label.append("  Không thi")

        label.appendTo(subjectDiv)
    }

    let table = $('<table>')
    let tableRow = $('<tr>')

    let subjectNameCell = $('<td>', {
        width: '80'
    })
    $("<span>", {
        text: subject.vietnamese + " "
    }).appendTo(subjectNameCell)
    subjectNameCell.appendTo(tableRow)

    let subjectMarkCell = $('<td>')
    $("<input>", {
        class: "rangeValues text-right"
    }).appendTo(subjectMarkCell)
    subjectMarkCell.appendTo(tableRow)

    tableRow.appendTo(table)
    table.appendTo(subjectDiv)

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

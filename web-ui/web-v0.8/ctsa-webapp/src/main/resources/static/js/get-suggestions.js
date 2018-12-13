function randomMark(min, max) {
    return Math.round((min + (max - min) * Math.random()) * 100) / 100
}

function retrieveMark(fieldId) {
    let subjectMarkDiv = $("#" + fieldId)

    let isChecked = subjectMarkDiv.find("input[type=checkbox]")

    if (!isChecked.is(":checked")) {
        let directMark = subjectMarkDiv.find("input.rangeValues").val()
        if (directMark.includes("-")) {
            let values = directMark.split("-")
            let min = parseFloat(values[0])
            let max = parseFloat(values[1])
            return randomMark(min, max)
        } else {
            return parseFloat(directMark)
        }
    } else {
        return null
    }
}

function getEntranceExamInput() {
    let entranceExamResults = []

    let subjects = JSON.parse(sessionStorage.getItem("entranceExamSubjects"))

    subjects.forEach(subject => {
        let entranceExamResult = createEntranceExamResult(
            subject.id,
            retrieveMark(subject.name + "Mark")
        )
        if (entranceExamResult.mark !== null) {
            entranceExamResults.push(entranceExamResult)
        }
    })

    return entranceExamResults
}

function getSuggestions() {
    let pupilInput = JSON.parse(sessionStorage.getItem("pupilInput"))

    pupilInput.entranceExamResults = getEntranceExamInput()

    axios
        .post(API_GATEWAY + CAREERS_SUGGESTIONS_SERVICE + "/suggestions/pupils", pupilInput)
        .then(response => {
            let suggestions = response.data
            sessionStorage.setItem("suggestions", JSON.stringify(suggestions))
            showSuggestions(suggestions)
            $("#scoreInputModal").modal("toggle")
        })
}

function createEntranceExamResult(subjectId, mark) {
    return {
        entranceExamSubjectId: subjectId,
        mark: mark,
    }
}

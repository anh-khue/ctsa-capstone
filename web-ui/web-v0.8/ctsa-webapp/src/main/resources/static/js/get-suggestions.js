function randomMark(min, max) {
    return Math.round((min + (max - min) * Math.random()) * 100) / 100;
}

function retrieveMark(fieldId) {
    let subjectMarkDiv = $("#" + fieldId);

    let isChecked = subjectMarkDiv.find("input[type=checkbox]");

    if (!isChecked.is(":checked")) {
        let directMark = subjectMarkDiv.find("input.rangeValues").val();
        if (directMark.includes("-")) {
            let values = directMark.split("-");
            let min = parseFloat(values[0]);
            let max = parseFloat(values[1]);
            return randomMark(min, max);
        } else {
            return parseFloat(directMark);
        }
    } else {
        return null;
    }
}

function getEntranceExamInput() {
    let entranceExamInput = {
        id: 0,
        entranceExamInputDetails: []
    };

    let subjects = JSON.parse(sessionStorage.getItem("entranceExamSubjects"));

    subjects.forEach(subject => {
        let entranceExamInputDetail = createEntranceExamInputDetail(
            subject.id,
            retrieveMark(subject.name + "Mark")
        );
        if (entranceExamInputDetail.mark !== null) {
            entranceExamInput.entranceExamInputDetails.push(entranceExamInputDetail);
        }
    });

    return entranceExamInput;
}

function getSuggestions() {
    let pupilInput = JSON.parse(sessionStorage.getItem("pupilInput"));

    pupilInput.entranceExamInput = getEntranceExamInput();

    axios
        .post(API_GATEWAY + RESULTS_SUGGESTIONS_SERVICE + "/suggestions/pupils", pupilInput)
        .then(response => {
            let suggestions = response.data;
            sessionStorage.setItem("suggestions", JSON.stringify(suggestions));
            showSuggestions(suggestions);
            $("#scoreInputModal").modal("toggle");
        });
}

function createEntranceExamInputDetail(subjectId, mark) {
    return {
        id: 0,
        entranceExamInputId: 0,
        entranceExamSubjectId: subjectId,
        mark: mark,
        entranceExamSubject: null
    };
}

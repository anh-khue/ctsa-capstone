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
    math: 0,
    literature: 0,
    english: 0,
    physics: null,
    chemistry: null,
    biology: null,
    history: null,
    geography: null
  };

  subjects.forEach(subject => {
    entranceExamInput[subject] = retrieveMark(subject + "Mark");
  });

  return entranceExamInput;
}

let subjects = [
  "math",
  "literature",
  "english",
  "physics",
  "chemistry",
  "biology",
  "history",
  "geography"
];

function getSuggestions() {
  let pupilInput = JSON.parse(sessionStorage.getItem("pupilInput"));

  let entranceExamInput = getEntranceExamInput();

  pupilInput.entranceExamInput = entranceExamInput;

  axios
    .post("http://localhost:8005/suggestions/pupils", pupilInput)
    .then(response => {
      let suggestions = response.data;
      showSuggestions(suggestions);
      $("#scoreInputModal").modal("toggle");
    });
}

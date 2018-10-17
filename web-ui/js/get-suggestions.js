function randomMark(min, max) {
  return Math.round((min + (max - min) * Math.random()) * 100) / 100;
}

function retrieveMark(fieldId) {
  let subjectMarkDiv = $(fieldId);

  let directMark = subjectMarkDiv.find("input.rangeValues").val();
  if (directMark.includes("-")) {
    let values = directMark.split("-");
    let min = parseFloat(values[0]);
    let max = parseFloat(values[1]);
    return randomMark(min, max);
  } else {
    return parseFloat(directMark);
  }
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

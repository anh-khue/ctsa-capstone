function retrieveSubjects(modalId) {
  let count = 0;

  let modal = $("#" + modalId);

  axios
    .get("http://localhost:8005/entrance-exam-subjects")
    .then(response => {
      let subjects = response.data;
      let subjectsPerCol = Math.ceil(subjects.length / 2);
      let col = $("<div>", {
        class: "col-md-6 pl-4"
      });

      subjects.forEach(subject => {
        count++;

        createSubjectInput(subject).appendTo(col);

        if (count % subjectsPerCol === 0) {
          col.appendTo(modal);
          col = $("<div>", {
            class: "col-md-6 pl-4"
          });
        }
      });
    })
    .then(() => {
      // Initialize Sliders
      var sliderSections = document.getElementsByClassName("range-slider");
      for (var x = 0; x < sliderSections.length; x++) {
        var sliders = sliderSections[x].getElementsByTagName("input");
        for (var y = 0; y < sliders.length; y++) {
          if (sliders[y].type === "range") {
            sliders[y].oninput = getVals;
            // Manually trigger event first time to display values
            sliders[y].oninput();
          }
        }
      }
    });
}

function createSubjectInput(subject) {
  let subjectDiv = $("<div>", {
    id: subject.name + "Mark",
    class: "collegeScoreInput"
  });

  $("<span>", {
    text: subject.vietnamese + " "
  }).appendTo(subjectDiv);

  $("<input>", {
    class: "rangeValues"
  }).appendTo(subjectDiv);

  if (!subject.required) {
    let label = $("<label>", {
      class: "float-right"
    });

    $("<input>", {
      type: "checkbox",
      value: "",
      onchange: "disableScoreInput(this)"
    }).appendTo(label);
    label.append("  Không thi");

    label.appendTo(subjectDiv);
  }

  let section = $("<section>", {
    style: "padding-bottom:20px",
    class: "range-slider"
  });

  $("<input>", {
    value: "5",
    min: "0",
    max: "10",
    step: "0.1",
    type: "range"
  }).appendTo(section);

  $("<input>", {
    value: "10",
    min: "0",
    max: "10",
    step: "0.1",
    type: "range"
  }).appendTo(section);

  section.appendTo(subjectDiv);

  return subjectDiv;
}

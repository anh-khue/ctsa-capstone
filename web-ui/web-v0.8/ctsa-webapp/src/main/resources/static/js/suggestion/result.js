function showSuggestions(suggestions) {
    for (let i = 0; i < 3;) {
        let suggestion = suggestions[i++]

        let suggestionDiv = $('#suggest' + i)
        suggestionDiv.empty()
        suggestionDiv.attr('onclick', 'showInfoModal(' + suggestion.major.id + ')')

        $('<br>').appendTo(suggestionDiv)
        $('<br>').appendTo(suggestionDiv)

        let wrapper = $('<div>', {
            class: 'job-result'
        })

        $('<b>', {
            text: suggestion.major.vietnamese,
            style: 'font-size:2em;color:rgb(235, 231, 231)'
        }).appendTo(wrapper)

        wrapper.appendTo(suggestionDiv)

        $('<br>').appendTo(suggestionDiv)
        $('<br>').appendTo(suggestionDiv)

        $('<img>', {
            src: suggestion.major.imageUrl,
            alt: suggestion.major.vietnamese,
        }).appendTo(suggestionDiv)
    }
}

async function showInfoModal(majorId) {
    let jobPredictionModal = $("#jobDescriptionModal")

    let majorInformation = await axios.get(API_GATEWAY + CAREERS_SERVICES + '/majors/' + majorId)

    let majorName = $('#majorName')
    majorName.empty()
    majorName.html(majorInformation.data.vietnamese)

    /* Salary */
    // Latest
    // let latestSalary = $('#latestSalary')
    // let latestSalaryResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/salaries/latest?majorId=' + majorId)
    //
    // let latestSalaryYear = $('#latestSalaryYear')
    // latestSalaryYear.empty()
    // latestSalaryYear.html("Số liệu " + latestSalaryResponse.data.year)
    //
    // latestSalary.empty()
    // latestSalary.html(numberWithCommas(latestSalaryResponse.data.actual) + '<br/>VNĐ')
    // // Prediction
    // let predictedSalary = $('#predictedSalary')
    // let predictedSalaryResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/salaries/predictions?year=2021&majorId=' + majorId)
    //
    // let predictedSalaryYear = $('#predictedSalaryYear')
    // predictedSalaryYear.empty()
    // predictedSalaryYear.html("Dự đoán " + predictedSalaryResponse.data.year)
    //
    // predictedSalary.empty()
    // predictedSalary.html(numberWithCommas(predictedSalaryResponse.data.forecast) + '<br/>VNĐ')

    /* Human Resource */
    // Latest
    let latestHumanResource = $('#latestHumanResource')
    let latestHumanResourceResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/human-resources/latest?majorId=' + majorId)

    let latestHumanResourceYear = $('#latestHumanResourceYear')
    latestHumanResourceYear.empty()
    latestHumanResourceYear.html("Số liệu " + latestHumanResourceResponse.data.year)

    latestHumanResource.empty()
    latestHumanResource.html(numberWithCommas(latestHumanResourceResponse.data.actual) + '<br/>người')
    // Prediction
    let predictedHumanResource = $('#predictedHumanResource')
    let predictedHumanResourceResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/human-resources/predictions?year=2021&majorId=' + majorId)

    let predictedHumanResourceYear = $('#predictedHumanResourceYear')
    predictedHumanResourceYear.empty()
    predictedHumanResourceYear.html("Dự đoán " + predictedHumanResourceResponse.data.year)

    predictedHumanResource.empty()
    let predictedHumanResourceData = numberWithCommas(Math.round(predictedHumanResourceResponse.data.forecast))
    predictedHumanResource.html(predictedHumanResourceData + '<br/>người')

    /* Supporting Information */
    // Latest
    let latestSupportingInformation = $('#latestSupportingInformation')
    let latestSupportingInformationResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/supporting-information/latest?majorId=' + majorId)

    let latestSupportingInformationYear = $('#latestSupportingInformationYear')
    latestSupportingInformationYear.empty()
    latestSupportingInformationYear.html("Số liệu " + latestSupportingInformationResponse.data.year)

    latestSupportingInformation.empty()
    latestSupportingInformation.html(numberWithCommas(latestSupportingInformationResponse.data.actual) + '<br/>'
        + latestSupportingInformationResponse.data.unit)
    // Prediction
    let predictedSupportingInformation = $('#predictedSupportingInformation')
    let predictedSupportingInformationResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/supporting-information/predictions?year=2021&majorId=' + majorId)

    let predictedSupportingInformationYear = $('#predictedSupportingInformationYear')
    predictedSupportingInformationYear.empty()
    predictedSupportingInformationYear.html("Dự đoán " + predictedSupportingInformationResponse.data.year)

    predictedSupportingInformation.empty()
    let predictedSupportingInformationData = numberWithCommas(Math.round(predictedSupportingInformationResponse.data.forecast))
    predictedSupportingInformation.html(predictedSupportingInformationData + '<br/>'
        + predictedSupportingInformationResponse.data.unit)

    getFinalPrediction(majorId, majorInformation.data.vietnamese,
        2021,
        Math.round(predictedHumanResourceResponse.data.forecast),
        Math.round(predictedSupportingInformationResponse.data.forecast))

    let detailButton = $('#detail-button')
    detailButton.attr('onclick', 'redirectToMajorDetail(' + majorId + ')')
    detailButton.html('Chi tiết ngành ' + majorInformation.data.vietnamese)

    jobPredictionModal.modal("toggle")
}

function redirectToMajorDetail(majorId) {
    window.location = '/nghe-nghiep/' + majorId
}

const numberWithCommas = (x) => {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

async function getFinalPrediction(majorId, majorName, year, predictedHumanResource, predictedSupportingInformation) {
    let majorStandardResponse = await axios.get(API_GATEWAY + CAREER_TREND_SERVICE + '/major_standards?majorId=' + majorId)

    let result
    let rawResult
    if (majorStandardResponse.data.unit.split("/")[1] === 'người') {
        rawResult = Math.round(predictedSupportingInformation / predictedHumanResource)
    } else {
        rawResult = Math.round(predictedHumanResource / predictedSupportingInformation)
    }

    let percentage = (Math.abs(rawResult - majorStandardResponse.data.standard) / majorStandardResponse.data.standard)
    console.log(percentage)
    result = numberWithCommas(rawResult)

    let finalResponse = 'So với chỉ tiêu ' + majorStandardResponse.data.standard + ' ' + majorStandardResponse.data.unit
        + '<br/>Đến năm ' + year + ', với số liệu dự đoán là ' + result + ' ' + majorStandardResponse.data.unit
        + '<br/>Ngành ' + majorName + ' có xu hướng '

    // information / human resource
    if (majorStandardResponse.data.unit.split("/")[1] === 'người') {
        if (rawResult < majorStandardResponse.data.standard && percentage > 0.2) {
            if (percentage > 1) {
                finalResponse += 'dư thừa rất nhiều nhân lực.'
            } else {
                finalResponse += 'dư thừa nhân lực.'
            }
        } else if (rawResult > majorStandardResponse.data.standard && percentage > 0.2) {
            if (percentage > 1) {
                finalResponse += 'thiếu hụt nhân lực trầm trọng.'
            } else {
                finalResponse += 'thiếu hụt nhân lực.'
            }
        } else {
            finalResponse += 'đáp ứng đủ nhân lực.'
        }
    } else { // human resource / information
        if (rawResult > majorStandardResponse.data.standard && percentage > 0.2) {
            if (percentage > 1) {
                finalResponse += 'bị dư thừa rất nhiều nhân lực.'
            } else {
                finalResponse += 'bị dư thừa nhân lực.'
            }
        } else if (rawResult < majorStandardResponse.data.standard && percentage > 0.2) {
            if (percentage > 1) {
                finalResponse += 'bị thiếu hụt nhân lực trầm trọng.'
            } else {
                finalResponse += 'bị thiếu hụt nhân lực.'
            }
        } else {
            finalResponse += 'đáp ứng đủ nhân lực.'
        }
    }

    let finalPrediction = $('#final-prediction')
    finalPrediction.empty()
    finalPrediction.html('' + finalResponse + '')
}

function disableScoreInput(obj) {
    let parent = obj.parentNode.parentNode;
    let section = parent.getElementsByTagName("section")
    if ($(obj).is(":checked")) {
        //disable range input
        $(section).children().prop('disabled', true)
        //disable text input
        $(parent).children().prop('disabled', true)
        $(parent).addClass("grey-out")
    } else {
        $(parent).removeClass("grey-out")
        $(parent).children().prop('disabled', false)
        $(parent).children().children().prop('disabled', false);
    }
}
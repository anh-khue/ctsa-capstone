const numberWithCommas = (x) => {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

async function getSkillsTrends() {
    let position = JSON.parse(sessionStorage.getItem('position'))
    let positionId = position.id
    let positionName = position.name

    let topSkillsResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE
        + '/required_skills?positionId=' + positionId + '&top=6')
    let topSkills = topSkillsResponse.data

    let predictionRequestBody = []
    for (let skill of topSkills) {
        let skillHistoryResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE
            + '/required_skills/history?positionId=' + positionId + '&skillId=' + skill)
        let skillHistory = skillHistoryResponse.data

        let skillHistoryWrapper = {
            skillId: skill,
            historicalData: skillHistory
        }
        predictionRequestBody.push(skillHistoryWrapper)
    }

    let predictionResponse = await axios.post(API_GATEWAY + CAREER_TREND_SERVICE
        + '/skills-trend?duration=4', predictionRequestBody)
    let prediction = predictionResponse.data

    generatePredictionsUI(positionName, prediction)
}

async function generatePredictionsUI(positionName, prediction) {
    let predictionsDiv = $('#predictions')
    predictionsDiv.empty()

    predictionsDiv.append(
        '<h3 class="text-center" style="margin-top: 1%">' +
        'Dự đoán trong 4 tháng tới<br/>Đây là các kỹ năng sẽ có nhu cầu tuyển dụng cao nhất<br/>đối với vị trí<br/>' +
        '<h1 class="text-center" style="font-weight: bold; margin-top: 1%">' + positionName + '</h1>' +
        '</h3>'
    )

    for (let i = 0; i < prediction.length; i = i + 2) {
        let row = $('<div>', {
            class: 'row',
            style: 'padding-top: 1%'
        })

        let trendLeft = prediction[i]
        let trendRight = prediction[i + 1]

        // Left item
        let skillLeftId = trendLeft.skillId

        let skillLeftResponse = await axios.get(API_GATEWAY + CAREERS_SERVICES + '/skills/' + skillLeftId)
        let skillLeft = skillLeftResponse.data
        let leftSkillInfo = $('<div>', {
            class: "col-md-4",
            style: "text-align: right; padding-right: 4%;"
        })
        $('<img>', {
            src: skillLeft.imageUrl,
            height: 120,
            width: 120
        }).appendTo(leftSkillInfo)
        $('<p>', {
            style: "font-size: 1.5em",
            html: skillLeft.skillType.vietnamese + ' ' + skillLeft.vietnamese
        }).appendTo(leftSkillInfo)
        leftSkillInfo.appendTo(row)

        let leftRecruitmentNumber = numberWithCommas(Math.round(trendLeft.predictedNumberOfRecruitment))
        let leftRecruitmentInfo = $('<div>', {
            class: "col-md-2 text-center"
        })
        let leftImage = $('<div>', {
            class: "statistic1"
        })
        $('<span>', {
            style: "color:rgb(235, 226, 226); font-size:5em",
            html: leftRecruitmentNumber
        }).appendTo(leftImage)
        $('<br/>').appendTo(leftImage)
        $('<span>', {
            style: "color:rgb(235, 226, 226); font-size:1.4em",
            html: 'Vị trí cần tuyển'
        }).appendTo(leftImage)
        leftImage.append('<br><br><br><br>')
        leftImage.appendTo(leftRecruitmentInfo)
        leftRecruitmentInfo.appendTo(row)


        // Right item
        let rightRecruitmentNumber = numberWithCommas(Math.round(trendRight.predictedNumberOfRecruitment))
        let rightRecruitmentInfo = $('<div>', {
            class: "col-md-2 text-center"
        })
        let rightImage = $('<div>', {
            class: "statistic2"
        })
        $('<span>', {
            style: "color:#212529; font-size:5em",
            html: rightRecruitmentNumber
        }).appendTo(rightImage)
        $('<br/>').appendTo(rightImage)
        $('<span>', {
            style: "color:#212529; font-size:1.4em",
            html: 'Vị trí cần tuyển'
        }).appendTo(rightImage)
        rightImage.append('<br><br><br><br>')
        rightImage.appendTo(rightRecruitmentInfo)
        rightRecruitmentInfo.appendTo(row)

        let skillRightId = trendRight.skillId

        let skillRightResponse = await axios.get(API_GATEWAY + CAREERS_SERVICES + '/skills/' + skillRightId)
        let skillRight = skillRightResponse.data
        let rightSkillInfo = $('<div>', {
            class: "col-md-4",
            style: "padding-left: 4%;"
        })
        $('<img>', {
            src: skillRight.imageUrl,
            height: 120,
            width: 120
        }).appendTo(rightSkillInfo)
        $('<p>', {
            style: "font-size: 1.5em",
            html: skillRight.skillType.vietnamese + ' ' + skillRight.vietnamese
        }).appendTo(rightSkillInfo)
        rightSkillInfo.appendTo(row)

        row.appendTo(predictionsDiv)
    }
}
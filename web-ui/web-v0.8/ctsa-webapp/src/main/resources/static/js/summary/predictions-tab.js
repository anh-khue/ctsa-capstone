const numberWithCommas = (x) => {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

async function getPositionTrend() {
    let position = JSON.parse(sessionStorage.getItem('position'))
    let positionId = position.id
    let positionName = position.name

    let positionHistoryResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE
        + '/recruitment/history?positionId=' + positionId)
    let positionHistory = positionHistoryResponse.data

    let positionPredictionResponse = await axios.post(API_GATEWAY + CAREER_TREND_SERVICE
        + '/position-trend?duration=4&positionId=' + positionId, positionHistory)
    let positionPrediction = positionPredictionResponse.data

    $("#position-prediction").html(numberWithCommas(Math.round(positionPrediction.forecast)) + ' ' + positionName)

    getSkillsTrends(position)
}

async function getSkillsTrends(position) {
    let positionId = position.id
    let positionName = position.name

    let topSkillsResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE
        + '/required_skills?positionId=' + positionId + '&top=10')
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
    generateAdvantages(prediction)
}

async function generatePredictionsUI(positionName, prediction) {
    let topSkillsDiv = $('#top-skills')
    topSkillsDiv.empty()

    let row
    for (let i = 0; i < 4; i++) {
        if (i % 2 === 0) {
            row = $('<div>', {
                class: 'row justify-content-center',
            })
        }

        let skillTrend = prediction[i]

        let skillId = skillTrend.skillId

        let skillResponse = await axios.get(API_GATEWAY + CAREERS_SERVICES + '/skills/' + skillId)
        let skill = skillResponse.data

        let skillCol = $('<div>', {
            class: 'col-md-6'
        })
        let skillRow = $('<div>', {
            class: 'row justify-content-center'
        })
        let skillImage = $('<div>', {
            class: 'col-md-3 statistic1 text-center'
        })
        let imageVerticalAlign = $('<div>', {
            class: 'vertical-middle',
            style: 'padding-bottom: 20%'
        })
        $('<img>', {
            src: skill.imageUrl,
            style: 'max-height: 120px; width: 80%'
        }).appendTo(imageVerticalAlign)
        imageVerticalAlign.appendTo(skillImage)
        skillImage.appendTo(skillRow)

        let skillDescription = $('<div>', {
            class: 'col-md-6 statistic-description text-center'
        })
        $('<div>', {
            class: 'vertical-middle',
            html: skill.name
        }).appendTo(skillDescription)
        skillDescription.appendTo(skillRow)

        skillRow.appendTo(skillCol)
        skillCol.appendTo(row)

        if (i % 2 === 0) {
            row.appendTo(topSkillsDiv)
        }
    }
}

async function generateAdvantages(prediction) {
    let advantagesDiv = $('#advantages')
    advantagesDiv.empty()

    for (let i = 4; i < 7; i++) {
        let skillTrend = prediction[i]

        let skillId = skillTrend.skillId

        let skillResponse = await axios.get(API_GATEWAY + CAREERS_SERVICES + '/skills/' + skillId)
        let skill = skillResponse.data

        let skillItem = $('<div>', {
            class: 'col-md-3 text-center',
            style: 'font-size: 1.1em; font-weight: bold'
        })
        $('<img>', {
            src: skill.imageUrl
        }).appendTo(skillItem)
        skillItem.append(skill.name)
        skillItem.appendTo(advantagesDiv)
    }
}
async function getITSoftwareTrendByPositionAndSkillType(positionId, skillTypeId) {
    let ids = []
    let response = await axios.get('http://localhost:8003/required_skills/top?position_id=' + positionId + '&skill_type_id=' + skillTypeId + "&page=1&skillsPerPage=3");
    if (response) {

        await response.data.forEach((id) => {
            ids.push(id)
        })

        if (ids.length > 0) {
            let postData = await axios.post('http://localhost:8001/skills/list', ids)
            return postData.data
        }
    }

    return null
}

function generateStatsUI(positionId, skills, direction) {
    let statsDiv = $('#stats')

    if (direction === 'left') {
        let banner = $('<div>', {
            class: "col-md-9 top-3 top-left",
            onclick: 'openSkillRankModal(' + positionId + ',' + skills[0].skillType.id + ',5)'
        })

        let row = $('<div>', {
            class: "row justify-content-between"
        })

        let skillTypeName = $('<div>', {
            class: "col-md-5 my-auto pl-5"
        })

        $('<b>', {
            style: "color: white; font-size: 40px;font-style: italic",
            html: "TOP<br>" + skills[0].skillType.vietnamese
        }).appendTo(skillTypeName)

        skillTypeName.appendTo(row)

        let skillItems = $('<div>', {
            class: "col-md-6 row justify-content-around top-icons-left"
        })

        for (let i = 0; i < 3; i++) {
            if (skills[i]) {
                let skillItem = $('<div>', {
                    class: "col-md-3 text-center",
                    style: "font-size: 1.1em; font-weight: bold"
                })

                $('<img>', {
                    src: skills[i].imageUrl
                }).appendTo(skillItem)
                skillItem.append(skills[i].vietnamese)

                skillItem.appendTo(skillItems)
            }
        }

        skillItems.appendTo(row)
        row.appendTo(banner)
        banner.appendTo(statsDiv)

        $('<div>', {
            class: "left-triangle"
        }).appendTo(statsDiv)
    } else {
        let banner = $('<div>', {
            class: "col-md-9 top-3 top-right",
            onclick: 'openSkillRankModal(' + positionId + ',' + skills[0].skillType.id + ',5)'
        })

        let row = $('<div>', {
            class: "row justify-content-between"
        })

        let skillItems = $('<div>', {
            class: "col-md-6 row justify-content-around top-icons-left"
        })

        for (let i = 0; i < 3; i++) {
            if (skills[i]) {
                let skillItem = $('<div>', {
                    class: "col-md-3 text-center",
                    style: "font-size: 1.1em; font-weight: bold"
                })

                $('<img>', {
                    src: skills[i].imageUrl
                }).appendTo(skillItem)
                skillItem.append(skills[i].vietnamese)

                skillItem.appendTo(skillItems)
            }
        }

        skillItems.appendTo(row)
        let skillTypeName = $('<div>', {
            class: "col-md-5 my-auto pl-5"
        })

        $('<b>', {
            style: "color: white; font-size: 40px;font-style: italic",
            html: "TOP<br>" + skills[0].skillType.vietnamese
        }).appendTo(skillTypeName)
        skillTypeName.appendTo(row)

        row.appendTo(banner)

        banner.appendTo(statsDiv)

        $('<div>', {
            class: "right-triangle"
        }).appendTo(statsDiv)
    }
}

async function openSkillRankModal(positionId, skillTypeId, skillsPerPage) {
    generatePagination(positionId, skillTypeId, skillsPerPage)

    showSkillRank(positionId, skillTypeId, 1, skillsPerPage)

    $('#skillRankModal').modal("toggle")
}

async function showSkillRank(positionId, skillTypeId, page, skillsPerPage) {
    $('.page-selected').removeClass('page-selected')
    $('#skillPage' + page).addClass('page-selected')

    let ids = []

    let idsResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE + '/required_skills/top?'
        + 'position_id=' + positionId
        + '&skill_type_id=' + skillTypeId
        + '&page=' + page
        + '&skillsPerPage=' + skillsPerPage)
    if (idsResponse) {
        for (let id of idsResponse.data) {
            ids.push(id)
        }
    }

    if (ids.length > 0) {
        let skillsResponse = await axios.post(API_GATEWAY + CAREERS_SERVICES + '/skills/list', ids)

        let skillRank = $('#skillRank')
        skillRank.empty()

        let skillTypeName = $('#skillTypeName')
        skillTypeName.empty()
        skillTypeName.html(skillsResponse.data[0].skillType.vietnamese)

        let skillTypeNameModal = $('#skillTypeNameModal')
        skillTypeNameModal.empty()
        skillTypeNameModal.html(skillsResponse.data[0].skillType.vietnamese)

        let count = (page - 1) * skillsPerPage
        for (let skill of skillsResponse.data) {
            let row = $('<tr>')

            $('<th>', {
                scope: 'row',
                class: 'text-center',
                html: ++count
            }).appendTo(row)

            $('<td>', {
                html: skill.vietnamese
            }).appendTo(row)

            row.appendTo(skillRank)
        }
    }
}

async function generatePagination(positionId, skillTypeId, skillsPerPage) {
    let totalPagesResponse = await axios.get(API_GATEWAY + WAREHOUSE_SERVICE + '/required_skills/total_pages/top?'
        + 'position_id=' + positionId
        + '&skill_type_id=' + skillTypeId
        + '&skillsPerPage=' + skillsPerPage)

    let totalPages = totalPagesResponse.data
    if (totalPages > 0) {
        let pagination = $('#pagination')
        pagination.empty()

        if (totalPages === 1) {
            let pageItem = $('<li>', {
                class: 'page-item'
            })

            $('<a>', {
                id: 'skillPage' + totalPages,
                class: 'page-link page-selected',
                href: '#',
                onclick: 'showSkillRank(' + positionId + ',' + skillTypeId + ',' + totalPages + ',' + skillsPerPage + ')',
                html: totalPages
            }).appendTo(pageItem)

            pageItem.appendTo(pagination)
        } else {
            // First page
            let firstPageItem = $('<li>', {
                class: 'page-item'
            })

            $('<a>', {
                class: 'page-link',
                href: '#',
                onclick: 'showSkillRank(' + positionId + ',' + skillTypeId + ',' + 1 + ',' + skillsPerPage + ')',
                html: 'First'
            }).appendTo(firstPageItem)

            firstPageItem.appendTo(pagination)

            for (let i = 1; i <= totalPages; i++) {
                let pageItem = $('<li>', {
                    class: 'page-item'
                })

                let pageLink = $('<a>', {
                    id: 'skillPage' + i,
                    class: 'page-link',
                    href: '#',
                    onclick: 'showSkillRank(' + positionId + ',' + skillTypeId + ',' + i + ',' + skillsPerPage + ')',
                    html: i
                })
                if (i === 1) {
                    pageLink.addClass('page-selected')
                }

                pageLink.appendTo(pageItem)

                pageItem.appendTo(pagination)
            }

            // Last page
            let lastPageItem = $('<li>', {
                class: 'page-item'
            })

            $('<a>', {
                class: 'page-link',
                href: '#',
                onclick: 'showSkillRank(' + positionId + ',' + skillTypeId + ',' + totalPages + ',' + skillsPerPage + ')',
                html: 'Last'
            }).appendTo(lastPageItem)

            lastPageItem.appendTo(pagination)
        }
    }
}
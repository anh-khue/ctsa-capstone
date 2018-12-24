function truncateText(text, maxLength) {
    if (text.length > maxLength) {
        let nextSpace = text.indexOf(' ', maxLength);
        text = text.substr(0, nextSpace) + '...';
    }

    return text;
}

function countDays(startDate) {
    let start = new Date(startDate);
    let today = new Date();
    let result = today.getDate() - start.getDate();

    if (result === 0) {
        return 'Hôm nay';
    } else {
        return result + ' ngày trước';
    }
}

function getListRecruitment(positionId) {
    let url = 'http://localhost:8080/company-management/recruitment/position/' + positionId;

    axios.get(url)
        .then(response => {
            let list = response.data

            for (let i = 0; i < list.length; i++) {
                let recruitment = '<div class="row justify-content-around stylish-panel">'
                    + '<div class="col-md-2 text-center my-auto">'
                    + '<a href="' + ('/tuyen-dung/' + list[i].id) + '"><img src="' + list[i].image + '" width="200" height="200"></a>'
                    + '</div>'
                    + '<div class="col-md-7">'
                    + '<a href="' + ('/tuyen-dung/' + list[i].id) + '"><h2>' + list[i].title + '</h2></a>'
                    + '<p class="job-descript">' + truncateText(list[i].jobDescription, 250) + '</p>'
                    + '<div class="pt-5">'

                for (let j = 0; j < list[i].skills.length; j++) {
                    recruitment += '<a href="#" class="btn btn-outline-dark">' + list[i].skills[j].skillName + '</a>&nbsp;'
                }

                recruitment += '</div>'
                    + '</div>'
                    + '<div class="col-md-2 my-auto" style="text-align: right">'
                    + '<div class="btn bg-orange text-white my-2">Hot</div>&nbsp;<div class="btn btn-primary my-2">New</div>'
                    + '<div class="my-4"><span>' + countDays(list[i].startDate) + '</span></div>'
                    + '<div class="my-4"><span>' + list[i].address + '</span></div>'
                    + '</div>'
                    + '</div>'

                $('.list-jobs').append(recruitment)
            }
        })
}
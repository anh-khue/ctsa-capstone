const API_AI_TOKEN = '24d58b6fb00e4cb4b05db32d3dd462ce';
const apiAiClient = require('apiai')(API_AI_TOKEN);
const FACEBOOK_ACCESS_TOKEN = 'EAAHywk70sd4BAGSgyDZA0WYKFv6fZB9aF9obIjpIsT8watzff4E58dhV36ZAxGL0GgDTpY8goUd3XmDcXbz4QgzSRHYWSzriwb7Y7lBFr4apZAGBtBqXQlNxbiBWqpCZBdP8HhUVXXCR2uPUS3YDVzwZBHg0ch4tHuNfmAlZCbreQZDZD';
const request = require('request');
const sendTextMessage = (senderId, text) => {
    request({
        url: 'https://graph.facebook.com/v2.6/me/messages',
        qs: { access_token: FACEBOOK_ACCESS_TOKEN },
        method: 'POST',
        json: {
            recipient: { id: senderId },
            message: { text },
        }
    });
};

module.exports = (event) => {
    const senderId = event.sender.id;
    const message = event.message.text;
    const apiaiSession = apiAiClient.textRequest(message, { sessionId: 'ctsachatbot_bot' });
    apiaiSession.on('response', (response) => {
        const result = response.result.fulfillment.speech;
        sendTextMessage(senderId, result);
        // console.log(response);
        request.post({
            url: 'http://localhost:8080/message',
            json: { msg : message }
        },
            function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    console.log(body);
                }
            }
        );
    });
    apiaiSession.on('error', error => console.log(error));
    apiaiSession.end();
};

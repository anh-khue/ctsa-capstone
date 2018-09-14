const API_AI_TOKEN = '24d58b6fb00e4cb4b05db32d3dd462ce';
const apiAiClient = require('apiai')(API_AI_TOKEN);
const FACEBOOK_ACCESS_TOKEN = 'EAAHywk70sd4BAL6jZCWkLZC26fRDfq8g494on2xpXYnM5rs0ZC5TDgZCj61VBfEC2IZASI5NO1dD9zfS6HhucnVc8n7OuMhAh9aXWb7v8LbjvptdKSWcH8Lez0FUh5ZAoUW8ebBwJ9rCosfe5xKDrYuJtKrzgQQY8pcqw9PGIuLwZDZD';
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
    });
    apiaiSession.on('error', error => console.log(error));
    apiaiSession.end();
};

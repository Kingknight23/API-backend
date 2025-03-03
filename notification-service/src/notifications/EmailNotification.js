// src/notifications/EmailNotification.js
class EmailNotification {
    constructor(recipient, subject, message) {
      this.recipient = recipient;
      this.subject = subject;
      this.message = message;
    }
  
    async send() {
      console.log(`Sending email to ${this.recipient}: ${this.subject} - ${this.message}`);
      // Integrate with SendGrid API here
    }
  }
  
  module.exports = EmailNotification;
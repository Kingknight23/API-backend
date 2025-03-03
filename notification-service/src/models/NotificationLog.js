// src/models/NotificationLog.js
const mongoose = require("mongoose");

const notificationLogSchema = new mongoose.Schema({
  user_id: { type: String, required: true },
  message_type: { type: String, required: true },
  timestamp: { type: Date, default: Date.now },
});

module.exports = mongoose.model("NotificationLog", notificationLogSchema);
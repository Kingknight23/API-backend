const express = require("express");
const notificationRoutes = require("./routes/notificationRoutes");

const app = express();
app.use(express.json());

// Routes
app.use("/api", notificationRoutes);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Notification service running on port ${PORT}`);
});
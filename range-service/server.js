const express = require("express");
const app = express();

const { sequelize } = require("./config/db");
const { errorHandler } = require("./config/errors");
const { isAuthenticated } = require("./middlewares/auth");
const { logRequest } = require("./middlewares/logger");
const { range } = require("./view/rangeView");

const PORT = process.env.PORT || 3000;

app.use(logRequest);

app.get("/range", isAuthenticated, range);

app.use("*", (req, res, next) => next(new Error("Url not found")));

app.use(errorHandler);

sequelize
    .authenticate()
    .then(() => {
        console.log("Connected to Database")
        app.listen(PORT, () => console.log(`Server running at PORT ${PORT}`));

        sequelize.sync({ force: true })
            .then(() => console.log("Database sync successful"))
            .catch((error) => console.log("Failed to sync the database"));
    })
    .catch((error) => console.log("Failed to connect the database:", error));
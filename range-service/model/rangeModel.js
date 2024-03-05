const { DataTypes } = require("sequelize")
const { sequelize } = require("../config/db");

const Range = sequelize.define("range", {
    start: { type: DataTypes.INTEGER, primaryKey: true },
    end: { type: DataTypes.INTEGER, primaryKey: true },
    allocated: { type: DataTypes.BOOLEAN, defaultValue: false }
});

module.exports = { Range };
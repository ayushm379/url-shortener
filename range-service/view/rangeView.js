const { Range } = require("../model/rangeModel");

const numberRange = parseInt(process.env.NUMBER_RANGE);

const range = (req, res, next) => {
    Range.max('end')
        .then(maxValue => {
            const newEntry = { start: maxValue + 1, end: maxValue + numberRange, allocated: true }
            Range.findOrCreate({
                    where: newEntry, 
                    defaults: newEntry
                })
                .then(([record, created]) => {
                    if(created) {
                        console.log("Created Data in Database ");
                    } else {
                        console.log("Data already present");
                        next(new Error('Error while creating data'));
                    }
                })
                .catch(error => {
                    console.log("Error while creating data")
                    next(new Error('Error while creating data', error));
                });
            res.status(200).json({
                success: true,
                range: {
                    start: newEntry.start,
                    end: newEntry.end
                }
            });
        })
        .catch(error => {
            console.error('Error finding max in database:', error);
            next(new Error('Error in database:', error))
        });

}

module.exports = {range};
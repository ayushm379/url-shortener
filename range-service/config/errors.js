const errorHandler = (error, req, res, next) => {
    console.error(error.stack);
    res.status(400).json({
        "success": false,
        "error": error.message
    });
}

module.exports = {errorHandler};
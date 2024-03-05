const jwt = require('jsonwebtoken');
const secretKey = process.env.SECRET_KEY;

const isAuthenticated = (req, res, next) => {
    const token = req.headers.authorization ? req.headers.authorization.split(' ')[1] : null;
    // if (!token) {
    //     next(new Error('Unauthorized: No token provided');
    // } else {
    //     jwt.verify(token, secretKey, (err, decoded) => {
    //         if (err) {
    //             next(new Error('Unauthorized: Invalid token');
    //         }
    //         req.user = decoded;
    //     });
    // }
    next(); 

};

module.exports = {isAuthenticated};
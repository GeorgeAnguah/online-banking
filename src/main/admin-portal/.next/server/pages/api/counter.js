"use strict";
(() => {
var exports = {};
exports.id = "pages/api/counter";
exports.ids = ["pages/api/counter"];
exports.modules = {

/***/ "./src/pages/api/counter.ts":
/*!**********************************!*\
  !*** ./src/pages/api/counter.ts ***!
  \**********************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
const countHandler = async (request, response) => {
  const {
    amount = 1
  } = request.body; // simulate IO latency

  await new Promise(resolve => setTimeout(resolve, 500));
  response.json({
    data: amount
  });
};

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (countHandler);

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
var __webpack_exports__ = (__webpack_exec__("./src/pages/api/counter.ts"));
module.exports = __webpack_exports__;

})();
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicGFnZXMvYXBpL2NvdW50ZXIuanMiLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFFQSxNQUFNQSxZQUE0QixHQUFHLE9BQU9DLE9BQVAsRUFBZ0JDLFFBQWhCLEtBQTZCO0FBQ2hFLFFBQU07QUFBRUMsSUFBQUEsTUFBTSxHQUFHO0FBQVgsTUFBaUJGLE9BQU8sQ0FBQ0csSUFBL0IsQ0FEZ0UsQ0FHaEU7O0FBQ0EsUUFBTSxJQUFJQyxPQUFKLENBQWFDLE9BQUQsSUFBYUMsVUFBVSxDQUFDRCxPQUFELEVBQVUsR0FBVixDQUFuQyxDQUFOO0FBRUFKLEVBQUFBLFFBQVEsQ0FBQ00sSUFBVCxDQUFjO0FBQUVDLElBQUFBLElBQUksRUFBRU47QUFBUixHQUFkO0FBQ0QsQ0FQRDs7QUFTQSxpRUFBZUgsWUFBZiIsInNvdXJjZXMiOlsid2VicGFjazovLy8uL3NyYy9wYWdlcy9hcGkvY291bnRlci50cyJdLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgdHlwZSB7IE5leHRBcGlIYW5kbGVyIH0gZnJvbSAnbmV4dCdcblxuY29uc3QgY291bnRIYW5kbGVyOiBOZXh0QXBpSGFuZGxlciA9IGFzeW5jIChyZXF1ZXN0LCByZXNwb25zZSkgPT4ge1xuICBjb25zdCB7IGFtb3VudCA9IDEgfSA9IHJlcXVlc3QuYm9keVxuXG4gIC8vIHNpbXVsYXRlIElPIGxhdGVuY3lcbiAgYXdhaXQgbmV3IFByb21pc2UoKHJlc29sdmUpID0+IHNldFRpbWVvdXQocmVzb2x2ZSwgNTAwKSlcblxuICByZXNwb25zZS5qc29uKHsgZGF0YTogYW1vdW50IH0pXG59XG5cbmV4cG9ydCBkZWZhdWx0IGNvdW50SGFuZGxlclxuIl0sIm5hbWVzIjpbImNvdW50SGFuZGxlciIsInJlcXVlc3QiLCJyZXNwb25zZSIsImFtb3VudCIsImJvZHkiLCJQcm9taXNlIiwicmVzb2x2ZSIsInNldFRpbWVvdXQiLCJqc29uIiwiZGF0YSJdLCJzb3VyY2VSb290IjoiIn0=
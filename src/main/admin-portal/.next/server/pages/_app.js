(() => {
var exports = {};
exports.id = "pages/_app";
exports.ids = ["pages/_app"];
exports.modules = {

/***/ "./src/app/store.ts":
/*!**************************!*\
  !*** ./src/app/store.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "makeStore": () => (/* binding */ makeStore),
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @reduxjs/toolkit */ "@reduxjs/toolkit");
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _features_counter_counterSlice__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../features/counter/counterSlice */ "./src/features/counter/counterSlice.ts");


function makeStore() {
  return (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.configureStore)({
    reducer: {
      counter: _features_counter_counterSlice__WEBPACK_IMPORTED_MODULE_1__.default
    }
  });
}
const store = makeStore();
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (store);

/***/ }),

/***/ "./src/features/counter/counterAPI.ts":
/*!********************************************!*\
  !*** ./src/features/counter/counterAPI.ts ***!
  \********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "fetchCount": () => (/* binding */ fetchCount)
/* harmony export */ });
async function fetchCount(amount = 1) {
  const response = await fetch('/api/counter', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      amount
    })
  });
  const result = await response.json();
  return result;
}

/***/ }),

/***/ "./src/features/counter/counterSlice.ts":
/*!**********************************************!*\
  !*** ./src/features/counter/counterSlice.ts ***!
  \**********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "incrementAsync": () => (/* binding */ incrementAsync),
/* harmony export */   "counterSlice": () => (/* binding */ counterSlice),
/* harmony export */   "increment": () => (/* binding */ increment),
/* harmony export */   "decrement": () => (/* binding */ decrement),
/* harmony export */   "incrementByAmount": () => (/* binding */ incrementByAmount),
/* harmony export */   "selectCount": () => (/* binding */ selectCount),
/* harmony export */   "incrementIfOdd": () => (/* binding */ incrementIfOdd),
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @reduxjs/toolkit */ "@reduxjs/toolkit");
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _counterAPI__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./counterAPI */ "./src/features/counter/counterAPI.ts");


const initialState = {
  value: 0,
  status: 'idle'
}; // The function below is called a thunk and allows us to perform async logic. It
// can be dispatched like a regular action: `dispatch(incrementAsync(10))`. This
// will call the thunk with the `dispatch` function as the first argument. Async
// code can then be executed and other actions can be dispatched. Thunks are
// typically used to make async requests.

const incrementAsync = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createAsyncThunk)('counter/fetchCount', async amount => {
  const response = await (0,_counterAPI__WEBPACK_IMPORTED_MODULE_1__.fetchCount)(amount); // The value we return becomes the `fulfilled` action payload

  return response.data;
});
const counterSlice = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createSlice)({
  name: 'counter',
  initialState,
  // The `reducers` field lets us define reducers and generate associated actions
  reducers: {
    increment: state => {
      // Redux Toolkit allows us to write "mutating" logic in reducers. It
      // doesn't actually mutate the state because it uses the Immer library,
      // which detects changes to a "draft state" and produces a brand new
      // immutable state based off those changes
      state.value += 1;
    },
    decrement: state => {
      state.value -= 1;
    },
    // Use the PayloadAction type to declare the contents of `action.payload`
    incrementByAmount: (state, action) => {
      state.value += action.payload;
    }
  },
  // The `extraReducers` field lets the slice handle actions defined elsewhere,
  // including actions generated by createAsyncThunk or in other slices.
  extraReducers: builder => {
    builder.addCase(incrementAsync.pending, state => {
      state.status = 'loading';
    }).addCase(incrementAsync.fulfilled, (state, action) => {
      state.status = 'idle';
      state.value += action.payload;
    });
  }
});
const {
  increment,
  decrement,
  incrementByAmount
} = counterSlice.actions; // The function below is called a selector and allows us to select a value from
// the state. Selectors can also be defined inline where they're used instead of
// in the slice file. For example: `useSelector((state: RootState) => state.counter.value)`

const selectCount = state => state.counter.value; // We can also write thunks by hand, which may contain both sync and async logic.
// Here's an example of conditionally dispatching actions based on current state.

const incrementIfOdd = amount => (dispatch, getState) => {
  const currentValue = selectCount(getState());

  if (currentValue % 2 === 1) {
    dispatch(incrementByAmount(amount));
  }
};
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (counterSlice.reducer);

/***/ }),

/***/ "./src/pages/_app.tsx":
/*!****************************!*\
  !*** ./src/pages/_app.tsx ***!
  \****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ "react/jsx-dev-runtime");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _styles_globals_css__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../styles/globals.css */ "./src/styles/globals.css");
/* harmony import */ var _styles_globals_css__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_styles_globals_css__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! react-redux */ "react-redux");
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(react_redux__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _app_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../app/store */ "./src/app/store.ts");

var _jsxFileName = "C:\\Users\\Eric Opoku\\IdeaProjects\\online-banking\\src\\main\\admin-portal\\src\\pages\\_app.tsx";

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }





function MyApp({
  Component,
  pageProps
}) {
  return /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(react_redux__WEBPACK_IMPORTED_MODULE_2__.Provider, {
    store: _app_store__WEBPACK_IMPORTED_MODULE_3__.default,
    children: /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(Component, _objectSpread({}, pageProps), void 0, false, {
      fileName: _jsxFileName,
      lineNumber: 11,
      columnNumber: 7
    }, this)
  }, void 0, false, {
    fileName: _jsxFileName,
    lineNumber: 10,
    columnNumber: 5
  }, this);
}

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (MyApp);

/***/ }),

/***/ "./src/styles/globals.css":
/*!********************************!*\
  !*** ./src/styles/globals.css ***!
  \********************************/
/***/ (() => {



/***/ }),

/***/ "@reduxjs/toolkit":
/*!***********************************!*\
  !*** external "@reduxjs/toolkit" ***!
  \***********************************/
/***/ ((module) => {

"use strict";
module.exports = require("@reduxjs/toolkit");

/***/ }),

/***/ "react-redux":
/*!******************************!*\
  !*** external "react-redux" ***!
  \******************************/
/***/ ((module) => {

"use strict";
module.exports = require("react-redux");

/***/ }),

/***/ "react/jsx-dev-runtime":
/*!****************************************!*\
  !*** external "react/jsx-dev-runtime" ***!
  \****************************************/
/***/ ((module) => {

"use strict";
module.exports = require("react/jsx-dev-runtime");

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
var __webpack_exports__ = (__webpack_exec__("./src/pages/_app.tsx"));
module.exports = __webpack_exports__;

})();
//# sourceMappingURL=data:application/json;charset=utf-8;base64,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V0FBVyxHQUFJWCxLQUFELElBQXFCQSxLQUFLLENBQUN4QixPQUFOLENBQWNnQixLQUF2RCxFQUVQO0FBQ0E7O0FBQ08sTUFBTW9CLGNBQWMsR0FBSWpDLE1BQUQsSUFBOEIsQ0FDMURrQyxRQUQwRCxFQUUxREMsUUFGMEQsS0FHdkQ7QUFDSCxRQUFNQyxZQUFZLEdBQUdKLFdBQVcsQ0FBQ0csUUFBUSxFQUFULENBQWhDOztBQUNBLE1BQUlDLFlBQVksR0FBRyxDQUFmLEtBQXFCLENBQXpCLEVBQTRCO0FBQzFCRixJQUFBQSxRQUFRLENBQUNYLGlCQUFpQixDQUFDdkIsTUFBRCxDQUFsQixDQUFSO0FBQ0Q7QUFDRixDQVJNO0FBVVAsaUVBQWVpQixZQUFZLENBQUNyQixPQUE1Qjs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQ2xGQTtBQUVBO0FBR0E7O0FBRUEsU0FBUzBDLEtBQVQsQ0FBZTtBQUFFQyxFQUFBQSxTQUFGO0FBQWFDLEVBQUFBO0FBQWIsQ0FBZixFQUFtRDtBQUNqRCxzQkFDRSw4REFBQyxpREFBRDtBQUFVLFNBQUssRUFBRTFDLCtDQUFqQjtBQUFBLDJCQUNFLDhEQUFDLFNBQUQsb0JBQWUwQyxTQUFmO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFERjtBQUFBO0FBQUE7QUFBQTtBQUFBLFVBREY7QUFLRDs7QUFFRCxpRUFBZUYsS0FBZjs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FFZkE7Ozs7Ozs7Ozs7O0FDQUE7Ozs7Ozs7Ozs7O0FDQUEiLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9zcmMvYXBwL3N0b3JlLnRzIiwid2VicGFjazovLy8uL3NyYy9mZWF0dXJlcy9jb3VudGVyL2NvdW50ZXJBUEkudHMiLCJ3ZWJwYWNrOi8vLy4vc3JjL2ZlYXR1cmVzL2NvdW50ZXIvY291bnRlclNsaWNlLnRzIiwid2VicGFjazovLy8uL3NyYy9wYWdlcy9fYXBwLnRzeCIsIndlYnBhY2s6Ly8vLi9zcmMvc3R5bGVzL2dsb2JhbHMuY3NzIiwid2VicGFjazovLy9leHRlcm5hbCBcIkByZWR1eGpzL3Rvb2xraXRcIiIsIndlYnBhY2s6Ly8vZXh0ZXJuYWwgXCJyZWFjdC1yZWR1eFwiIiwid2VicGFjazovLy9leHRlcm5hbCBcInJlYWN0L2pzeC1kZXYtcnVudGltZVwiIl0sInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IGNvbmZpZ3VyZVN0b3JlLCBUaHVua0FjdGlvbiwgQWN0aW9uIH0gZnJvbSAnQHJlZHV4anMvdG9vbGtpdCdcblxuaW1wb3J0IGNvdW50ZXJSZWR1Y2VyIGZyb20gJy4uL2ZlYXR1cmVzL2NvdW50ZXIvY291bnRlclNsaWNlJ1xuXG5leHBvcnQgZnVuY3Rpb24gbWFrZVN0b3JlKCkge1xuICByZXR1cm4gY29uZmlndXJlU3RvcmUoe1xuICAgIHJlZHVjZXI6IHsgY291bnRlcjogY291bnRlclJlZHVjZXIgfSxcbiAgfSlcbn1cblxuY29uc3Qgc3RvcmUgPSBtYWtlU3RvcmUoKVxuXG5leHBvcnQgdHlwZSBBcHBTdGF0ZSA9IFJldHVyblR5cGU8dHlwZW9mIHN0b3JlLmdldFN0YXRlPlxuXG5leHBvcnQgdHlwZSBBcHBEaXNwYXRjaCA9IHR5cGVvZiBzdG9yZS5kaXNwYXRjaFxuXG5leHBvcnQgdHlwZSBBcHBUaHVuazxSZXR1cm5UeXBlID0gdm9pZD4gPSBUaHVua0FjdGlvbjxcbiAgUmV0dXJuVHlwZSxcbiAgQXBwU3RhdGUsXG4gIHVua25vd24sXG4gIEFjdGlvbjxzdHJpbmc+XG4+XG5cbmV4cG9ydCBkZWZhdWx0IHN0b3JlXG4iLCJleHBvcnQgYXN5bmMgZnVuY3Rpb24gZmV0Y2hDb3VudChhbW91bnQgPSAxKTogUHJvbWlzZTx7IGRhdGE6IG51bWJlciB9PiB7XG4gIGNvbnN0IHJlc3BvbnNlID0gYXdhaXQgZmV0Y2goJy9hcGkvY291bnRlcicsIHtcbiAgICBtZXRob2Q6ICdQT1NUJyxcbiAgICBoZWFkZXJzOiB7XG4gICAgICAnQ29udGVudC1UeXBlJzogJ2FwcGxpY2F0aW9uL2pzb24nLFxuICAgIH0sXG4gICAgYm9keTogSlNPTi5zdHJpbmdpZnkoeyBhbW91bnQgfSksXG4gIH0pXG4gIGNvbnN0IHJlc3VsdCA9IGF3YWl0IHJlc3BvbnNlLmpzb24oKVxuXG4gIHJldHVybiByZXN1bHRcbn1cbiIsImltcG9ydCB7IGNyZWF0ZUFzeW5jVGh1bmssIGNyZWF0ZVNsaWNlLCBQYXlsb2FkQWN0aW9uIH0gZnJvbSAnQHJlZHV4anMvdG9vbGtpdCdcblxuaW1wb3J0IHR5cGUgeyBBcHBTdGF0ZSwgQXBwVGh1bmsgfSBmcm9tICcuLi8uLi9hcHAvc3RvcmUnXG5pbXBvcnQgeyBmZXRjaENvdW50IH0gZnJvbSAnLi9jb3VudGVyQVBJJ1xuXG5leHBvcnQgaW50ZXJmYWNlIENvdW50ZXJTdGF0ZSB7XG4gIHZhbHVlOiBudW1iZXJcbiAgc3RhdHVzOiAnaWRsZScgfCAnbG9hZGluZycgfCAnZmFpbGVkJ1xufVxuXG5jb25zdCBpbml0aWFsU3RhdGU6IENvdW50ZXJTdGF0ZSA9IHtcbiAgdmFsdWU6IDAsXG4gIHN0YXR1czogJ2lkbGUnLFxufVxuXG4vLyBUaGUgZnVuY3Rpb24gYmVsb3cgaXMgY2FsbGVkIGEgdGh1bmsgYW5kIGFsbG93cyB1cyB0byBwZXJmb3JtIGFzeW5jIGxvZ2ljLiBJdFxuLy8gY2FuIGJlIGRpc3BhdGNoZWQgbGlrZSBhIHJlZ3VsYXIgYWN0aW9uOiBgZGlzcGF0Y2goaW5jcmVtZW50QXN5bmMoMTApKWAuIFRoaXNcbi8vIHdpbGwgY2FsbCB0aGUgdGh1bmsgd2l0aCB0aGUgYGRpc3BhdGNoYCBmdW5jdGlvbiBhcyB0aGUgZmlyc3QgYXJndW1lbnQuIEFzeW5jXG4vLyBjb2RlIGNhbiB0aGVuIGJlIGV4ZWN1dGVkIGFuZCBvdGhlciBhY3Rpb25zIGNhbiBiZSBkaXNwYXRjaGVkLiBUaHVua3MgYXJlXG4vLyB0eXBpY2FsbHkgdXNlZCB0byBtYWtlIGFzeW5jIHJlcXVlc3RzLlxuZXhwb3J0IGNvbnN0IGluY3JlbWVudEFzeW5jID0gY3JlYXRlQXN5bmNUaHVuayhcbiAgJ2NvdW50ZXIvZmV0Y2hDb3VudCcsXG4gIGFzeW5jIChhbW91bnQ6IG51bWJlcikgPT4ge1xuICAgIGNvbnN0IHJlc3BvbnNlID0gYXdhaXQgZmV0Y2hDb3VudChhbW91bnQpXG4gICAgLy8gVGhlIHZhbHVlIHdlIHJldHVybiBiZWNvbWVzIHRoZSBgZnVsZmlsbGVkYCBhY3Rpb24gcGF5bG9hZFxuICAgIHJldHVybiByZXNwb25zZS5kYXRhXG4gIH1cbilcblxuZXhwb3J0IGNvbnN0IGNvdW50ZXJTbGljZSA9IGNyZWF0ZVNsaWNlKHtcbiAgbmFtZTogJ2NvdW50ZXInLFxuICBpbml0aWFsU3RhdGUsXG4gIC8vIFRoZSBgcmVkdWNlcnNgIGZpZWxkIGxldHMgdXMgZGVmaW5lIHJlZHVjZXJzIGFuZCBnZW5lcmF0ZSBhc3NvY2lhdGVkIGFjdGlvbnNcbiAgcmVkdWNlcnM6IHtcbiAgICBpbmNyZW1lbnQ6IChzdGF0ZSkgPT4ge1xuICAgICAgLy8gUmVkdXggVG9vbGtpdCBhbGxvd3MgdXMgdG8gd3JpdGUgXCJtdXRhdGluZ1wiIGxvZ2ljIGluIHJlZHVjZXJzLiBJdFxuICAgICAgLy8gZG9lc24ndCBhY3R1YWxseSBtdXRhdGUgdGhlIHN0YXRlIGJlY2F1c2UgaXQgdXNlcyB0aGUgSW1tZXIgbGlicmFyeSxcbiAgICAgIC8vIHdoaWNoIGRldGVjdHMgY2hhbmdlcyB0byBhIFwiZHJhZnQgc3RhdGVcIiBhbmQgcHJvZHVjZXMgYSBicmFuZCBuZXdcbiAgICAgIC8vIGltbXV0YWJsZSBzdGF0ZSBiYXNlZCBvZmYgdGhvc2UgY2hhbmdlc1xuICAgICAgc3RhdGUudmFsdWUgKz0gMVxuICAgIH0sXG4gICAgZGVjcmVtZW50OiAoc3RhdGUpID0+IHtcbiAgICAgIHN0YXRlLnZhbHVlIC09IDFcbiAgICB9LFxuICAgIC8vIFVzZSB0aGUgUGF5bG9hZEFjdGlvbiB0eXBlIHRvIGRlY2xhcmUgdGhlIGNvbnRlbnRzIG9mIGBhY3Rpb24ucGF5bG9hZGBcbiAgICBpbmNyZW1lbnRCeUFtb3VudDogKHN0YXRlLCBhY3Rpb246IFBheWxvYWRBY3Rpb248bnVtYmVyPikgPT4ge1xuICAgICAgc3RhdGUudmFsdWUgKz0gYWN0aW9uLnBheWxvYWRcbiAgICB9LFxuICB9LFxuICAvLyBUaGUgYGV4dHJhUmVkdWNlcnNgIGZpZWxkIGxldHMgdGhlIHNsaWNlIGhhbmRsZSBhY3Rpb25zIGRlZmluZWQgZWxzZXdoZXJlLFxuICAvLyBpbmNsdWRpbmcgYWN0aW9ucyBnZW5lcmF0ZWQgYnkgY3JlYXRlQXN5bmNUaHVuayBvciBpbiBvdGhlciBzbGljZXMuXG4gIGV4dHJhUmVkdWNlcnM6IChidWlsZGVyKSA9PiB7XG4gICAgYnVpbGRlclxuICAgICAgLmFkZENhc2UoaW5jcmVtZW50QXN5bmMucGVuZGluZywgKHN0YXRlKSA9PiB7XG4gICAgICAgIHN0YXRlLnN0YXR1cyA9ICdsb2FkaW5nJ1xuICAgICAgfSlcbiAgICAgIC5hZGRDYXNlKGluY3JlbWVudEFzeW5jLmZ1bGZpbGxlZCwgKHN0YXRlLCBhY3Rpb24pID0+IHtcbiAgICAgICAgc3RhdGUuc3RhdHVzID0gJ2lkbGUnXG4gICAgICAgIHN0YXRlLnZhbHVlICs9IGFjdGlvbi5wYXlsb2FkXG4gICAgICB9KVxuICB9LFxufSlcblxuZXhwb3J0IGNvbnN0IHsgaW5jcmVtZW50LCBkZWNyZW1lbnQsIGluY3JlbWVudEJ5QW1vdW50IH0gPSBjb3VudGVyU2xpY2UuYWN0aW9uc1xuXG4vLyBUaGUgZnVuY3Rpb24gYmVsb3cgaXMgY2FsbGVkIGEgc2VsZWN0b3IgYW5kIGFsbG93cyB1cyB0byBzZWxlY3QgYSB2YWx1ZSBmcm9tXG4vLyB0aGUgc3RhdGUuIFNlbGVjdG9ycyBjYW4gYWxzbyBiZSBkZWZpbmVkIGlubGluZSB3aGVyZSB0aGV5J3JlIHVzZWQgaW5zdGVhZCBvZlxuLy8gaW4gdGhlIHNsaWNlIGZpbGUuIEZvciBleGFtcGxlOiBgdXNlU2VsZWN0b3IoKHN0YXRlOiBSb290U3RhdGUpID0+IHN0YXRlLmNvdW50ZXIudmFsdWUpYFxuZXhwb3J0IGNvbnN0IHNlbGVjdENvdW50ID0gKHN0YXRlOiBBcHBTdGF0ZSkgPT4gc3RhdGUuY291bnRlci52YWx1ZVxuXG4vLyBXZSBjYW4gYWxzbyB3cml0ZSB0aHVua3MgYnkgaGFuZCwgd2hpY2ggbWF5IGNvbnRhaW4gYm90aCBzeW5jIGFuZCBhc3luYyBsb2dpYy5cbi8vIEhlcmUncyBhbiBleGFtcGxlIG9mIGNvbmRpdGlvbmFsbHkgZGlzcGF0Y2hpbmcgYWN0aW9ucyBiYXNlZCBvbiBjdXJyZW50IHN0YXRlLlxuZXhwb3J0IGNvbnN0IGluY3JlbWVudElmT2RkID0gKGFtb3VudDogbnVtYmVyKTogQXBwVGh1bmsgPT4gKFxuICBkaXNwYXRjaCxcbiAgZ2V0U3RhdGVcbikgPT4ge1xuICBjb25zdCBjdXJyZW50VmFsdWUgPSBzZWxlY3RDb3VudChnZXRTdGF0ZSgpKVxuICBpZiAoY3VycmVudFZhbHVlICUgMiA9PT0gMSkge1xuICAgIGRpc3BhdGNoKGluY3JlbWVudEJ5QW1vdW50KGFtb3VudCkpXG4gIH1cbn1cblxuZXhwb3J0IGRlZmF1bHQgY291bnRlclNsaWNlLnJlZHVjZXJcbiIsImltcG9ydCAnLi4vc3R5bGVzL2dsb2JhbHMuY3NzJ1xuXG5pbXBvcnQgeyBQcm92aWRlciB9IGZyb20gJ3JlYWN0LXJlZHV4J1xuaW1wb3J0IHR5cGUgeyBBcHBQcm9wcyB9IGZyb20gJ25leHQvYXBwJ1xuXG5pbXBvcnQgc3RvcmUgZnJvbSAnLi4vYXBwL3N0b3JlJ1xuXG5mdW5jdGlvbiBNeUFwcCh7IENvbXBvbmVudCwgcGFnZVByb3BzIH06IEFwcFByb3BzKSB7XG4gIHJldHVybiAoXG4gICAgPFByb3ZpZGVyIHN0b3JlPXtzdG9yZX0+XG4gICAgICA8Q29tcG9uZW50IHsuLi5wYWdlUHJvcHN9IC8+XG4gICAgPC9Qcm92aWRlcj5cbiAgKVxufVxuXG5leHBvcnQgZGVmYXVsdCBNeUFwcFxuIiwiIiwibW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiQHJlZHV4anMvdG9vbGtpdFwiKTsiLCJtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCJyZWFjdC1yZWR1eFwiKTsiLCJtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCJyZWFjdC9qc3gtZGV2LXJ1bnRpbWVcIik7Il0sIm5hbWVzIjpbImNvbmZpZ3VyZVN0b3JlIiwiY291bnRlclJlZHVjZXIiLCJtYWtlU3RvcmUiLCJyZWR1Y2VyIiwiY291bnRlciIsInN0b3JlIiwiZmV0Y2hDb3VudCIsImFtb3VudCIsInJlc3BvbnNlIiwiZmV0Y2giLCJtZXRob2QiLCJoZWFkZXJzIiwiYm9keSIsIkpTT04iLCJzdHJpbmdpZnkiLCJyZXN1bHQiLCJqc29uIiwiY3JlYXRlQXN5bmNUaHVuayIsImNyZWF0ZVNsaWNlIiwiaW5pdGlhbFN0YXRlIiwidmFsdWUiLCJzdGF0dXMiLCJpbmNyZW1lbnRBc3luYyIsImRhdGEiLCJjb3VudGVyU2xpY2UiLCJuYW1lIiwicmVkdWNlcnMiLCJpbmNyZW1lbnQiLCJzdGF0ZSIsImRlY3JlbWVudCIsImluY3JlbWVudEJ5QW1vdW50IiwiYWN0aW9uIiwicGF5bG9hZCIsImV4dHJhUmVkdWNlcnMiLCJidWlsZGVyIiwiYWRkQ2FzZSIsInBlbmRpbmciLCJmdWxmaWxsZWQiLCJhY3Rpb25zIiwic2VsZWN0Q291bnQiLCJpbmNyZW1lbnRJZk9kZCIsImRpc3BhdGNoIiwiZ2V0U3RhdGUiLCJjdXJyZW50VmFsdWUiLCJQcm92aWRlciIsIk15QXBwIiwiQ29tcG9uZW50IiwicGFnZVByb3BzIl0sInNvdXJjZVJvb3QiOiIifQ==
(() => {
var exports = {};
exports.id = "pages/index";
exports.ids = ["pages/index"];
exports.modules = {

/***/ "./src/app/hooks.ts":
/*!**************************!*\
  !*** ./src/app/hooks.ts ***!
  \**************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "useForm": () => (/* binding */ useForm),
/* harmony export */   "useInterval": () => (/* binding */ useInterval),
/* harmony export */   "useAppDispatch": () => (/* binding */ useAppDispatch),
/* harmony export */   "useAppSelector": () => (/* binding */ useAppSelector)
/* harmony export */ });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-redux */ "react-redux");
/* harmony import */ var react_redux__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react_redux__WEBPACK_IMPORTED_MODULE_1__);
function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }



const useForm = defaultValues => handler => async event => {
  event.preventDefault();
  event.persist();
  const form = event.target;
  const elements = Array.from(form.elements);
  const data = elements.filter(element => element.hasAttribute('name')).reduce((object, element) => _objectSpread(_objectSpread({}, object), {}, {
    [`${element.getAttribute('name')}`]: element.value
  }), defaultValues);
  await handler(data);
  form.reset();
}; // https://overreacted.io/making-setinterval-declarative-with-react-hooks/

const useInterval = (callback, delay) => {
  const savedCallback = (0,react__WEBPACK_IMPORTED_MODULE_0__.useRef)();
  (0,react__WEBPACK_IMPORTED_MODULE_0__.useEffect)(() => {
    savedCallback.current = callback;
  }, [callback]);
  (0,react__WEBPACK_IMPORTED_MODULE_0__.useEffect)(() => {
    const handler = (...args) => {
      var _savedCallback$curren;

      return (_savedCallback$curren = savedCallback.current) === null || _savedCallback$curren === void 0 ? void 0 : _savedCallback$curren.call(savedCallback, ...args);
    };

    if (delay !== null) {
      const id = setInterval(handler, delay);
      return () => clearInterval(id);
    }
  }, [delay]);
}; // Use throughout your app instead of plain `useDispatch` and `useSelector`

const useAppDispatch = () => (0,react_redux__WEBPACK_IMPORTED_MODULE_1__.useDispatch)();
const useAppSelector = react_redux__WEBPACK_IMPORTED_MODULE_1__.useSelector;

/***/ }),

/***/ "./src/features/counter/Counter.tsx":
/*!******************************************!*\
  !*** ./src/features/counter/Counter.tsx ***!
  \******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ "react/jsx-dev-runtime");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _app_hooks__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../app/hooks */ "./src/app/hooks.ts");
/* harmony import */ var _counterSlice__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./counterSlice */ "./src/features/counter/counterSlice.ts");
/* harmony import */ var _Counter_module_css__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./Counter.module.css */ "./src/features/counter/Counter.module.css");
/* harmony import */ var _Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(_Counter_module_css__WEBPACK_IMPORTED_MODULE_4__);

var _jsxFileName = "C:\\Users\\Eric Opoku\\IdeaProjects\\online-banking\\src\\main\\admin-portal\\src\\features\\counter\\Counter.tsx";





function Counter() {
  const dispatch = (0,_app_hooks__WEBPACK_IMPORTED_MODULE_2__.useAppDispatch)();
  const count = (0,_app_hooks__WEBPACK_IMPORTED_MODULE_2__.useAppSelector)(_counterSlice__WEBPACK_IMPORTED_MODULE_3__.selectCount);
  const {
    0: incrementAmount,
    1: setIncrementAmount
  } = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)('2');
  const incrementValue = Number(incrementAmount) || 0;
  return /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("div", {
    children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("div", {
      className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().row),
      children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("button", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().button),
        "aria-label": "Decrement value",
        onClick: () => dispatch((0,_counterSlice__WEBPACK_IMPORTED_MODULE_3__.decrement)()),
        children: "-"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 24,
        columnNumber: 9
      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().value),
        children: count
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 31,
        columnNumber: 9
      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("button", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().button),
        "aria-label": "Increment value",
        onClick: () => dispatch((0,_counterSlice__WEBPACK_IMPORTED_MODULE_3__.increment)()),
        children: "+"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 32,
        columnNumber: 9
      }, this)]
    }, void 0, true, {
      fileName: _jsxFileName,
      lineNumber: 23,
      columnNumber: 7
    }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("div", {
      className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().row),
      children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("input", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().textbox),
        "aria-label": "Set increment amount",
        value: incrementAmount,
        onChange: e => setIncrementAmount(e.target.value)
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 41,
        columnNumber: 9
      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("button", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().button),
        onClick: () => dispatch((0,_counterSlice__WEBPACK_IMPORTED_MODULE_3__.incrementByAmount)(incrementValue)),
        children: "Add Amount"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 47,
        columnNumber: 9
      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("button", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().asyncButton),
        onClick: () => dispatch((0,_counterSlice__WEBPACK_IMPORTED_MODULE_3__.incrementAsync)(incrementValue)),
        children: "Add Async"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 53,
        columnNumber: 9
      }, this), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("button", {
        className: (_Counter_module_css__WEBPACK_IMPORTED_MODULE_4___default().button),
        onClick: () => dispatch((0,_counterSlice__WEBPACK_IMPORTED_MODULE_3__.incrementIfOdd)(incrementValue)),
        children: "Add If Odd"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 59,
        columnNumber: 9
      }, this)]
    }, void 0, true, {
      fileName: _jsxFileName,
      lineNumber: 40,
      columnNumber: 7
    }, this)]
  }, void 0, true, {
    fileName: _jsxFileName,
    lineNumber: 22,
    columnNumber: 5
  }, this);
}

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (Counter);

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

/***/ "./src/pages/index.tsx":
/*!*****************************!*\
  !*** ./src/pages/index.tsx ***!
  \*****************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "default": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react/jsx-dev-runtime */ "react/jsx-dev-runtime");
/* harmony import */ var react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! next/head */ "next/head");
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_head__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _features_counter_Counter__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../features/counter/Counter */ "./src/features/counter/Counter.tsx");
/* harmony import */ var _styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../styles/Home.module.css */ "./src/styles/Home.module.css");
/* harmony import */ var _styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3__);

var _jsxFileName = "C:\\Users\\Eric Opoku\\IdeaProjects\\online-banking\\src\\main\\admin-portal\\src\\pages\\index.tsx";




const IndexPage = () => {
  return /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("div", {
    className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().container),
    children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)((next_head__WEBPACK_IMPORTED_MODULE_1___default()), {
      children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("title", {
        children: "Redux Toolkit"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 11,
        columnNumber: 9
      }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("link", {
        rel: "icon",
        href: "/favicon.ico"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 12,
        columnNumber: 9
      }, undefined)]
    }, void 0, true, {
      fileName: _jsxFileName,
      lineNumber: 10,
      columnNumber: 7
    }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("header", {
      className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().header),
      children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("img", {
        src: "/logo.svg",
        className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().logo),
        alt: "logo"
      }, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 15,
        columnNumber: 9
      }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)(_features_counter_Counter__WEBPACK_IMPORTED_MODULE_2__.default, {}, void 0, false, {
        fileName: _jsxFileName,
        lineNumber: 16,
        columnNumber: 9
      }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("p", {
        children: ["Edit ", /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("code", {
          children: "src/App.tsx"
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 18,
          columnNumber: 16
        }, undefined), " and save to reload."]
      }, void 0, true, {
        fileName: _jsxFileName,
        lineNumber: 17,
        columnNumber: 9
      }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
        children: [/*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
          children: "Learn "
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 21,
          columnNumber: 11
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("a", {
          className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().link),
          href: "https://reactjs.org/",
          target: "_blank",
          rel: "noopener noreferrer",
          children: "React"
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 22,
          columnNumber: 11
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
          children: ", "
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 30,
          columnNumber: 11
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("a", {
          className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().link),
          href: "https://redux.js.org/",
          target: "_blank",
          rel: "noopener noreferrer",
          children: "Redux"
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 31,
          columnNumber: 11
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
          children: ", "
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 39,
          columnNumber: 11
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("a", {
          className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().link),
          href: "https://redux-toolkit.js.org/",
          target: "_blank",
          rel: "noopener noreferrer",
          children: "Redux Toolkit"
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 40,
          columnNumber: 11
        }, undefined), ",", /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("span", {
          children: " and "
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 48,
          columnNumber: 12
        }, undefined), /*#__PURE__*/(0,react_jsx_dev_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxDEV)("a", {
          className: (_styles_Home_module_css__WEBPACK_IMPORTED_MODULE_3___default().link),
          href: "https://react-redux.js.org/",
          target: "_blank",
          rel: "noopener noreferrer",
          children: "React Redux"
        }, void 0, false, {
          fileName: _jsxFileName,
          lineNumber: 49,
          columnNumber: 11
        }, undefined)]
      }, void 0, true, {
        fileName: _jsxFileName,
        lineNumber: 20,
        columnNumber: 9
      }, undefined)]
    }, void 0, true, {
      fileName: _jsxFileName,
      lineNumber: 14,
      columnNumber: 7
    }, undefined)]
  }, void 0, true, {
    fileName: _jsxFileName,
    lineNumber: 9,
    columnNumber: 5
  }, undefined);
};

/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (IndexPage);

/***/ }),

/***/ "./src/features/counter/Counter.module.css":
/*!*************************************************!*\
  !*** ./src/features/counter/Counter.module.css ***!
  \*************************************************/
/***/ ((module) => {

// Exports
module.exports = {
	"row": "Counter_row__2owYy",
	"value": "Counter_value__19Ztg",
	"button": "Counter_button__BtJ_w",
	"textbox": "Counter_textbox__1lols",
	"asyncButton": "Counter_asyncButton__1bB7D Counter_button__BtJ_w"
};


/***/ }),

/***/ "./src/styles/Home.module.css":
/*!************************************!*\
  !*** ./src/styles/Home.module.css ***!
  \************************************/
/***/ ((module) => {

// Exports
module.exports = {
	"container": "Home_container__sDexO",
	"logo": "Home_logo__qxZJS",
	"header": "Home_header__1runh",
	"link": "Home_link__3d5W4",
	"logo-float": "Home_logo-float__1uLqM"
};


/***/ }),

/***/ "@reduxjs/toolkit":
/*!***********************************!*\
  !*** external "@reduxjs/toolkit" ***!
  \***********************************/
/***/ ((module) => {

"use strict";
module.exports = require("@reduxjs/toolkit");

/***/ }),

/***/ "next/head":
/*!****************************!*\
  !*** external "next/head" ***!
  \****************************/
/***/ ((module) => {

"use strict";
module.exports = require("next/head");

/***/ }),

/***/ "react":
/*!************************!*\
  !*** external "react" ***!
  \************************/
/***/ ((module) => {

"use strict";
module.exports = require("react");

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
var __webpack_exports__ = (__webpack_exec__("./src/pages/index.tsx"));
module.exports = __webpack_exports__;

})();
//# sourceMappingURL=data:application/json;charset=utf-8;base64,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QUFBQSxxQkFuQkYsZUFvQkU7QUFDRSxtQkFBUyxFQUFFQSxxRUFEYjtBQUVFLGNBQUksRUFBQywrQkFGUDtBQUdFLGdCQUFNLEVBQUMsUUFIVDtBQUlFLGFBQUcsRUFBQyxxQkFKTjtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQSxxQkFwQkYsb0JBNEJHO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLHFCQTVCSCxlQTZCRTtBQUNFLG1CQUFTLEVBQUVBLHFFQURiO0FBRUUsY0FBSSxFQUFDLDZCQUZQO0FBR0UsZ0JBQU0sRUFBQyxRQUhUO0FBSUUsYUFBRyxFQUFDLHFCQUpOO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLHFCQTdCRjtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUEsbUJBTkY7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBLGlCQUxGO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQSxlQURGO0FBcURELENBdEREOztBQXdEQSxpRUFBZTZDLFNBQWY7Ozs7Ozs7Ozs7QUM5REE7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTs7Ozs7Ozs7Ozs7QUNQQTtBQUNBO0FBQ0E7QUFDQTtBQUNBO0FBQ0E7QUFDQTtBQUNBOzs7Ozs7Ozs7Ozs7QUNQQTs7Ozs7Ozs7Ozs7QUNBQTs7Ozs7Ozs7Ozs7QUNBQTs7Ozs7Ozs7Ozs7QUNBQTs7Ozs7Ozs7Ozs7QUNBQSIsInNvdXJjZXMiOlsid2VicGFjazovLy8uL3NyYy9hcHAvaG9va3MudHMiLCJ3ZWJwYWNrOi8vLy4vc3JjL2ZlYXR1cmVzL2NvdW50ZXIvQ291bnRlci50c3giLCJ3ZWJwYWNrOi8vLy4vc3JjL2ZlYXR1cmVzL2NvdW50ZXIvY291bnRlckFQSS50cyIsIndlYnBhY2s6Ly8vLi9zcmMvZmVhdHVyZXMvY291bnRlci9jb3VudGVyU2xpY2UudHMiLCJ3ZWJwYWNrOi8vLy4vc3JjL3BhZ2VzL2luZGV4LnRzeCIsIndlYnBhY2s6Ly8vLi9zcmMvZmVhdHVyZXMvY291bnRlci9Db3VudGVyLm1vZHVsZS5jc3MiLCJ3ZWJwYWNrOi8vLy4vc3JjL3N0eWxlcy9Ib21lLm1vZHVsZS5jc3MiLCJ3ZWJwYWNrOi8vL2V4dGVybmFsIFwiQHJlZHV4anMvdG9vbGtpdFwiIiwid2VicGFjazovLy9leHRlcm5hbCBcIm5leHQvaGVhZFwiIiwid2VicGFjazovLy9leHRlcm5hbCBcInJlYWN0XCIiLCJ3ZWJwYWNrOi8vL2V4dGVybmFsIFwicmVhY3QtcmVkdXhcIiIsIndlYnBhY2s6Ly8vZXh0ZXJuYWwgXCJyZWFjdC9qc3gtZGV2LXJ1bnRpbWVcIiJdLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgdHlwZSB7IENoYW5nZUV2ZW50IH0gZnJvbSAncmVhY3QnXG5pbXBvcnQgeyB1c2VFZmZlY3QsIHVzZVJlZiB9IGZyb20gJ3JlYWN0J1xuaW1wb3J0IHsgVHlwZWRVc2VTZWxlY3Rvckhvb2ssIHVzZURpc3BhdGNoLCB1c2VTZWxlY3RvciB9IGZyb20gJ3JlYWN0LXJlZHV4J1xuXG5pbXBvcnQgdHlwZSB7IEFwcERpc3BhdGNoLCBBcHBTdGF0ZSB9IGZyb20gJy4vc3RvcmUnXG5cbmV4cG9ydCBjb25zdCB1c2VGb3JtID0gPFRDb250ZW50PihkZWZhdWx0VmFsdWVzOiBUQ29udGVudCkgPT4gKFxuICBoYW5kbGVyOiAoY29udGVudDogVENvbnRlbnQpID0+IHZvaWRcbikgPT4gYXN5bmMgKGV2ZW50OiBDaGFuZ2VFdmVudDxIVE1MRm9ybUVsZW1lbnQ+KSA9PiB7XG4gIGV2ZW50LnByZXZlbnREZWZhdWx0KClcbiAgZXZlbnQucGVyc2lzdCgpXG5cbiAgY29uc3QgZm9ybSA9IGV2ZW50LnRhcmdldCBhcyBIVE1MRm9ybUVsZW1lbnRcbiAgY29uc3QgZWxlbWVudHMgPSBBcnJheS5mcm9tKGZvcm0uZWxlbWVudHMpIGFzIEhUTUxJbnB1dEVsZW1lbnRbXVxuICBjb25zdCBkYXRhID0gZWxlbWVudHNcbiAgICAuZmlsdGVyKChlbGVtZW50KSA9PiBlbGVtZW50Lmhhc0F0dHJpYnV0ZSgnbmFtZScpKVxuICAgIC5yZWR1Y2UoXG4gICAgICAob2JqZWN0LCBlbGVtZW50KSA9PiAoe1xuICAgICAgICAuLi5vYmplY3QsXG4gICAgICAgIFtgJHtlbGVtZW50LmdldEF0dHJpYnV0ZSgnbmFtZScpfWBdOiBlbGVtZW50LnZhbHVlLFxuICAgICAgfSksXG4gICAgICBkZWZhdWx0VmFsdWVzXG4gICAgKVxuICBhd2FpdCBoYW5kbGVyKGRhdGEpXG4gIGZvcm0ucmVzZXQoKVxufVxuXG4vLyBodHRwczovL292ZXJyZWFjdGVkLmlvL21ha2luZy1zZXRpbnRlcnZhbC1kZWNsYXJhdGl2ZS13aXRoLXJlYWN0LWhvb2tzL1xuZXhwb3J0IGNvbnN0IHVzZUludGVydmFsID0gKGNhbGxiYWNrOiBGdW5jdGlvbiwgZGVsYXk6IG51bWJlcikgPT4ge1xuICBjb25zdCBzYXZlZENhbGxiYWNrID0gdXNlUmVmPEZ1bmN0aW9uPigpXG4gIHVzZUVmZmVjdCgoKSA9PiB7XG4gICAgc2F2ZWRDYWxsYmFjay5jdXJyZW50ID0gY2FsbGJhY2tcbiAgfSwgW2NhbGxiYWNrXSlcbiAgdXNlRWZmZWN0KCgpID0+IHtcbiAgICBjb25zdCBoYW5kbGVyID0gKC4uLmFyZ3M6IGFueSkgPT4gc2F2ZWRDYWxsYmFjay5jdXJyZW50Py4oLi4uYXJncylcblxuICAgIGlmIChkZWxheSAhPT0gbnVsbCkge1xuICAgICAgY29uc3QgaWQgPSBzZXRJbnRlcnZhbChoYW5kbGVyLCBkZWxheSlcbiAgICAgIHJldHVybiAoKSA9PiBjbGVhckludGVydmFsKGlkKVxuICAgIH1cbiAgfSwgW2RlbGF5XSlcbn1cblxuLy8gVXNlIHRocm91Z2hvdXQgeW91ciBhcHAgaW5zdGVhZCBvZiBwbGFpbiBgdXNlRGlzcGF0Y2hgIGFuZCBgdXNlU2VsZWN0b3JgXG5leHBvcnQgY29uc3QgdXNlQXBwRGlzcGF0Y2ggPSAoKSA9PiB1c2VEaXNwYXRjaDxBcHBEaXNwYXRjaD4oKVxuXG5leHBvcnQgY29uc3QgdXNlQXBwU2VsZWN0b3I6IFR5cGVkVXNlU2VsZWN0b3JIb29rPEFwcFN0YXRlPiA9IHVzZVNlbGVjdG9yXG4iLCJpbXBvcnQgeyB1c2VTdGF0ZSB9IGZyb20gJ3JlYWN0J1xuXG5pbXBvcnQgeyB1c2VBcHBTZWxlY3RvciwgdXNlQXBwRGlzcGF0Y2ggfSBmcm9tICcuLi8uLi9hcHAvaG9va3MnXG5pbXBvcnQge1xuICBkZWNyZW1lbnQsXG4gIGluY3JlbWVudCxcbiAgaW5jcmVtZW50QnlBbW91bnQsXG4gIGluY3JlbWVudEFzeW5jLFxuICBpbmNyZW1lbnRJZk9kZCxcbiAgc2VsZWN0Q291bnQsXG59IGZyb20gJy4vY291bnRlclNsaWNlJ1xuaW1wb3J0IHN0eWxlcyBmcm9tICcuL0NvdW50ZXIubW9kdWxlLmNzcydcblxuZnVuY3Rpb24gQ291bnRlcigpIHtcbiAgY29uc3QgZGlzcGF0Y2ggPSB1c2VBcHBEaXNwYXRjaCgpXG4gIGNvbnN0IGNvdW50ID0gdXNlQXBwU2VsZWN0b3Ioc2VsZWN0Q291bnQpXG4gIGNvbnN0IFtpbmNyZW1lbnRBbW91bnQsIHNldEluY3JlbWVudEFtb3VudF0gPSB1c2VTdGF0ZSgnMicpXG5cbiAgY29uc3QgaW5jcmVtZW50VmFsdWUgPSBOdW1iZXIoaW5jcmVtZW50QW1vdW50KSB8fCAwXG5cbiAgcmV0dXJuIChcbiAgICA8ZGl2PlxuICAgICAgPGRpdiBjbGFzc05hbWU9e3N0eWxlcy5yb3d9PlxuICAgICAgICA8YnV0dG9uXG4gICAgICAgICAgY2xhc3NOYW1lPXtzdHlsZXMuYnV0dG9ufVxuICAgICAgICAgIGFyaWEtbGFiZWw9XCJEZWNyZW1lbnQgdmFsdWVcIlxuICAgICAgICAgIG9uQ2xpY2s9eygpID0+IGRpc3BhdGNoKGRlY3JlbWVudCgpKX1cbiAgICAgICAgPlxuICAgICAgICAgIC1cbiAgICAgICAgPC9idXR0b24+XG4gICAgICAgIDxzcGFuIGNsYXNzTmFtZT17c3R5bGVzLnZhbHVlfT57Y291bnR9PC9zcGFuPlxuICAgICAgICA8YnV0dG9uXG4gICAgICAgICAgY2xhc3NOYW1lPXtzdHlsZXMuYnV0dG9ufVxuICAgICAgICAgIGFyaWEtbGFiZWw9XCJJbmNyZW1lbnQgdmFsdWVcIlxuICAgICAgICAgIG9uQ2xpY2s9eygpID0+IGRpc3BhdGNoKGluY3JlbWVudCgpKX1cbiAgICAgICAgPlxuICAgICAgICAgICtcbiAgICAgICAgPC9idXR0b24+XG4gICAgICA8L2Rpdj5cbiAgICAgIDxkaXYgY2xhc3NOYW1lPXtzdHlsZXMucm93fT5cbiAgICAgICAgPGlucHV0XG4gICAgICAgICAgY2xhc3NOYW1lPXtzdHlsZXMudGV4dGJveH1cbiAgICAgICAgICBhcmlhLWxhYmVsPVwiU2V0IGluY3JlbWVudCBhbW91bnRcIlxuICAgICAgICAgIHZhbHVlPXtpbmNyZW1lbnRBbW91bnR9XG4gICAgICAgICAgb25DaGFuZ2U9eyhlKSA9PiBzZXRJbmNyZW1lbnRBbW91bnQoZS50YXJnZXQudmFsdWUpfVxuICAgICAgICAvPlxuICAgICAgICA8YnV0dG9uXG4gICAgICAgICAgY2xhc3NOYW1lPXtzdHlsZXMuYnV0dG9ufVxuICAgICAgICAgIG9uQ2xpY2s9eygpID0+IGRpc3BhdGNoKGluY3JlbWVudEJ5QW1vdW50KGluY3JlbWVudFZhbHVlKSl9XG4gICAgICAgID5cbiAgICAgICAgICBBZGQgQW1vdW50XG4gICAgICAgIDwvYnV0dG9uPlxuICAgICAgICA8YnV0dG9uXG4gICAgICAgICAgY2xhc3NOYW1lPXtzdHlsZXMuYXN5bmNCdXR0b259XG4gICAgICAgICAgb25DbGljaz17KCkgPT4gZGlzcGF0Y2goaW5jcmVtZW50QXN5bmMoaW5jcmVtZW50VmFsdWUpKX1cbiAgICAgICAgPlxuICAgICAgICAgIEFkZCBBc3luY1xuICAgICAgICA8L2J1dHRvbj5cbiAgICAgICAgPGJ1dHRvblxuICAgICAgICAgIGNsYXNzTmFtZT17c3R5bGVzLmJ1dHRvbn1cbiAgICAgICAgICBvbkNsaWNrPXsoKSA9PiBkaXNwYXRjaChpbmNyZW1lbnRJZk9kZChpbmNyZW1lbnRWYWx1ZSkpfVxuICAgICAgICA+XG4gICAgICAgICAgQWRkIElmIE9kZFxuICAgICAgICA8L2J1dHRvbj5cbiAgICAgIDwvZGl2PlxuICAgIDwvZGl2PlxuICApXG59XG5cbmV4cG9ydCBkZWZhdWx0IENvdW50ZXJcbiIsImV4cG9ydCBhc3luYyBmdW5jdGlvbiBmZXRjaENvdW50KGFtb3VudCA9IDEpOiBQcm9taXNlPHsgZGF0YTogbnVtYmVyIH0+IHtcbiAgY29uc3QgcmVzcG9uc2UgPSBhd2FpdCBmZXRjaCgnL2FwaS9jb3VudGVyJywge1xuICAgIG1ldGhvZDogJ1BPU1QnLFxuICAgIGhlYWRlcnM6IHtcbiAgICAgICdDb250ZW50LVR5cGUnOiAnYXBwbGljYXRpb24vanNvbicsXG4gICAgfSxcbiAgICBib2R5OiBKU09OLnN0cmluZ2lmeSh7IGFtb3VudCB9KSxcbiAgfSlcbiAgY29uc3QgcmVzdWx0ID0gYXdhaXQgcmVzcG9uc2UuanNvbigpXG5cbiAgcmV0dXJuIHJlc3VsdFxufVxuIiwiaW1wb3J0IHsgY3JlYXRlQXN5bmNUaHVuaywgY3JlYXRlU2xpY2UsIFBheWxvYWRBY3Rpb24gfSBmcm9tICdAcmVkdXhqcy90b29sa2l0J1xuXG5pbXBvcnQgdHlwZSB7IEFwcFN0YXRlLCBBcHBUaHVuayB9IGZyb20gJy4uLy4uL2FwcC9zdG9yZSdcbmltcG9ydCB7IGZldGNoQ291bnQgfSBmcm9tICcuL2NvdW50ZXJBUEknXG5cbmV4cG9ydCBpbnRlcmZhY2UgQ291bnRlclN0YXRlIHtcbiAgdmFsdWU6IG51bWJlclxuICBzdGF0dXM6ICdpZGxlJyB8ICdsb2FkaW5nJyB8ICdmYWlsZWQnXG59XG5cbmNvbnN0IGluaXRpYWxTdGF0ZTogQ291bnRlclN0YXRlID0ge1xuICB2YWx1ZTogMCxcbiAgc3RhdHVzOiAnaWRsZScsXG59XG5cbi8vIFRoZSBmdW5jdGlvbiBiZWxvdyBpcyBjYWxsZWQgYSB0aHVuayBhbmQgYWxsb3dzIHVzIHRvIHBlcmZvcm0gYXN5bmMgbG9naWMuIEl0XG4vLyBjYW4gYmUgZGlzcGF0Y2hlZCBsaWtlIGEgcmVndWxhciBhY3Rpb246IGBkaXNwYXRjaChpbmNyZW1lbnRBc3luYygxMCkpYC4gVGhpc1xuLy8gd2lsbCBjYWxsIHRoZSB0aHVuayB3aXRoIHRoZSBgZGlzcGF0Y2hgIGZ1bmN0aW9uIGFzIHRoZSBmaXJzdCBhcmd1bWVudC4gQXN5bmNcbi8vIGNvZGUgY2FuIHRoZW4gYmUgZXhlY3V0ZWQgYW5kIG90aGVyIGFjdGlvbnMgY2FuIGJlIGRpc3BhdGNoZWQuIFRodW5rcyBhcmVcbi8vIHR5cGljYWxseSB1c2VkIHRvIG1ha2UgYXN5bmMgcmVxdWVzdHMuXG5leHBvcnQgY29uc3QgaW5jcmVtZW50QXN5bmMgPSBjcmVhdGVBc3luY1RodW5rKFxuICAnY291bnRlci9mZXRjaENvdW50JyxcbiAgYXN5bmMgKGFtb3VudDogbnVtYmVyKSA9PiB7XG4gICAgY29uc3QgcmVzcG9uc2UgPSBhd2FpdCBmZXRjaENvdW50KGFtb3VudClcbiAgICAvLyBUaGUgdmFsdWUgd2UgcmV0dXJuIGJlY29tZXMgdGhlIGBmdWxmaWxsZWRgIGFjdGlvbiBwYXlsb2FkXG4gICAgcmV0dXJuIHJlc3BvbnNlLmRhdGFcbiAgfVxuKVxuXG5leHBvcnQgY29uc3QgY291bnRlclNsaWNlID0gY3JlYXRlU2xpY2Uoe1xuICBuYW1lOiAnY291bnRlcicsXG4gIGluaXRpYWxTdGF0ZSxcbiAgLy8gVGhlIGByZWR1Y2Vyc2AgZmllbGQgbGV0cyB1cyBkZWZpbmUgcmVkdWNlcnMgYW5kIGdlbmVyYXRlIGFzc29jaWF0ZWQgYWN0aW9uc1xuICByZWR1Y2Vyczoge1xuICAgIGluY3JlbWVudDogKHN0YXRlKSA9PiB7XG4gICAgICAvLyBSZWR1eCBUb29sa2l0IGFsbG93cyB1cyB0byB3cml0ZSBcIm11dGF0aW5nXCIgbG9naWMgaW4gcmVkdWNlcnMuIEl0XG4gICAgICAvLyBkb2Vzbid0IGFjdHVhbGx5IG11dGF0ZSB0aGUgc3RhdGUgYmVjYXVzZSBpdCB1c2VzIHRoZSBJbW1lciBsaWJyYXJ5LFxuICAgICAgLy8gd2hpY2ggZGV0ZWN0cyBjaGFuZ2VzIHRvIGEgXCJkcmFmdCBzdGF0ZVwiIGFuZCBwcm9kdWNlcyBhIGJyYW5kIG5ld1xuICAgICAgLy8gaW1tdXRhYmxlIHN0YXRlIGJhc2VkIG9mZiB0aG9zZSBjaGFuZ2VzXG4gICAgICBzdGF0ZS52YWx1ZSArPSAxXG4gICAgfSxcbiAgICBkZWNyZW1lbnQ6IChzdGF0ZSkgPT4ge1xuICAgICAgc3RhdGUudmFsdWUgLT0gMVxuICAgIH0sXG4gICAgLy8gVXNlIHRoZSBQYXlsb2FkQWN0aW9uIHR5cGUgdG8gZGVjbGFyZSB0aGUgY29udGVudHMgb2YgYGFjdGlvbi5wYXlsb2FkYFxuICAgIGluY3JlbWVudEJ5QW1vdW50OiAoc3RhdGUsIGFjdGlvbjogUGF5bG9hZEFjdGlvbjxudW1iZXI+KSA9PiB7XG4gICAgICBzdGF0ZS52YWx1ZSArPSBhY3Rpb24ucGF5bG9hZFxuICAgIH0sXG4gIH0sXG4gIC8vIFRoZSBgZXh0cmFSZWR1Y2Vyc2AgZmllbGQgbGV0cyB0aGUgc2xpY2UgaGFuZGxlIGFjdGlvbnMgZGVmaW5lZCBlbHNld2hlcmUsXG4gIC8vIGluY2x1ZGluZyBhY3Rpb25zIGdlbmVyYXRlZCBieSBjcmVhdGVBc3luY1RodW5rIG9yIGluIG90aGVyIHNsaWNlcy5cbiAgZXh0cmFSZWR1Y2VyczogKGJ1aWxkZXIpID0+IHtcbiAgICBidWlsZGVyXG4gICAgICAuYWRkQ2FzZShpbmNyZW1lbnRBc3luYy5wZW5kaW5nLCAoc3RhdGUpID0+IHtcbiAgICAgICAgc3RhdGUuc3RhdHVzID0gJ2xvYWRpbmcnXG4gICAgICB9KVxuICAgICAgLmFkZENhc2UoaW5jcmVtZW50QXN5bmMuZnVsZmlsbGVkLCAoc3RhdGUsIGFjdGlvbikgPT4ge1xuICAgICAgICBzdGF0ZS5zdGF0dXMgPSAnaWRsZSdcbiAgICAgICAgc3RhdGUudmFsdWUgKz0gYWN0aW9uLnBheWxvYWRcbiAgICAgIH0pXG4gIH0sXG59KVxuXG5leHBvcnQgY29uc3QgeyBpbmNyZW1lbnQsIGRlY3JlbWVudCwgaW5jcmVtZW50QnlBbW91bnQgfSA9IGNvdW50ZXJTbGljZS5hY3Rpb25zXG5cbi8vIFRoZSBmdW5jdGlvbiBiZWxvdyBpcyBjYWxsZWQgYSBzZWxlY3RvciBhbmQgYWxsb3dzIHVzIHRvIHNlbGVjdCBhIHZhbHVlIGZyb21cbi8vIHRoZSBzdGF0ZS4gU2VsZWN0b3JzIGNhbiBhbHNvIGJlIGRlZmluZWQgaW5saW5lIHdoZXJlIHRoZXkncmUgdXNlZCBpbnN0ZWFkIG9mXG4vLyBpbiB0aGUgc2xpY2UgZmlsZS4gRm9yIGV4YW1wbGU6IGB1c2VTZWxlY3Rvcigoc3RhdGU6IFJvb3RTdGF0ZSkgPT4gc3RhdGUuY291bnRlci52YWx1ZSlgXG5leHBvcnQgY29uc3Qgc2VsZWN0Q291bnQgPSAoc3RhdGU6IEFwcFN0YXRlKSA9PiBzdGF0ZS5jb3VudGVyLnZhbHVlXG5cbi8vIFdlIGNhbiBhbHNvIHdyaXRlIHRodW5rcyBieSBoYW5kLCB3aGljaCBtYXkgY29udGFpbiBib3RoIHN5bmMgYW5kIGFzeW5jIGxvZ2ljLlxuLy8gSGVyZSdzIGFuIGV4YW1wbGUgb2YgY29uZGl0aW9uYWxseSBkaXNwYXRjaGluZyBhY3Rpb25zIGJhc2VkIG9uIGN1cnJlbnQgc3RhdGUuXG5leHBvcnQgY29uc3QgaW5jcmVtZW50SWZPZGQgPSAoYW1vdW50OiBudW1iZXIpOiBBcHBUaHVuayA9PiAoXG4gIGRpc3BhdGNoLFxuICBnZXRTdGF0ZVxuKSA9PiB7XG4gIGNvbnN0IGN1cnJlbnRWYWx1ZSA9IHNlbGVjdENvdW50KGdldFN0YXRlKCkpXG4gIGlmIChjdXJyZW50VmFsdWUgJSAyID09PSAxKSB7XG4gICAgZGlzcGF0Y2goaW5jcmVtZW50QnlBbW91bnQoYW1vdW50KSlcbiAgfVxufVxuXG5leHBvcnQgZGVmYXVsdCBjb3VudGVyU2xpY2UucmVkdWNlclxuIiwiaW1wb3J0IHR5cGUgeyBOZXh0UGFnZSB9IGZyb20gJ25leHQnXG5pbXBvcnQgSGVhZCBmcm9tICduZXh0L2hlYWQnXG5cbmltcG9ydCBDb3VudGVyIGZyb20gJy4uL2ZlYXR1cmVzL2NvdW50ZXIvQ291bnRlcidcbmltcG9ydCBzdHlsZXMgZnJvbSAnLi4vc3R5bGVzL0hvbWUubW9kdWxlLmNzcydcblxuY29uc3QgSW5kZXhQYWdlOiBOZXh0UGFnZSA9ICgpID0+IHtcbiAgcmV0dXJuIChcbiAgICA8ZGl2IGNsYXNzTmFtZT17c3R5bGVzLmNvbnRhaW5lcn0+XG4gICAgICA8SGVhZD5cbiAgICAgICAgPHRpdGxlPlJlZHV4IFRvb2xraXQ8L3RpdGxlPlxuICAgICAgICA8bGluayByZWw9XCJpY29uXCIgaHJlZj1cIi9mYXZpY29uLmljb1wiIC8+XG4gICAgICA8L0hlYWQ+XG4gICAgICA8aGVhZGVyIGNsYXNzTmFtZT17c3R5bGVzLmhlYWRlcn0+XG4gICAgICAgIDxpbWcgc3JjPVwiL2xvZ28uc3ZnXCIgY2xhc3NOYW1lPXtzdHlsZXMubG9nb30gYWx0PVwibG9nb1wiIC8+XG4gICAgICAgIDxDb3VudGVyIC8+XG4gICAgICAgIDxwPlxuICAgICAgICAgIEVkaXQgPGNvZGU+c3JjL0FwcC50c3g8L2NvZGU+IGFuZCBzYXZlIHRvIHJlbG9hZC5cbiAgICAgICAgPC9wPlxuICAgICAgICA8c3Bhbj5cbiAgICAgICAgICA8c3Bhbj5MZWFybiA8L3NwYW4+XG4gICAgICAgICAgPGFcbiAgICAgICAgICAgIGNsYXNzTmFtZT17c3R5bGVzLmxpbmt9XG4gICAgICAgICAgICBocmVmPVwiaHR0cHM6Ly9yZWFjdGpzLm9yZy9cIlxuICAgICAgICAgICAgdGFyZ2V0PVwiX2JsYW5rXCJcbiAgICAgICAgICAgIHJlbD1cIm5vb3BlbmVyIG5vcmVmZXJyZXJcIlxuICAgICAgICAgID5cbiAgICAgICAgICAgIFJlYWN0XG4gICAgICAgICAgPC9hPlxuICAgICAgICAgIDxzcGFuPiwgPC9zcGFuPlxuICAgICAgICAgIDxhXG4gICAgICAgICAgICBjbGFzc05hbWU9e3N0eWxlcy5saW5rfVxuICAgICAgICAgICAgaHJlZj1cImh0dHBzOi8vcmVkdXguanMub3JnL1wiXG4gICAgICAgICAgICB0YXJnZXQ9XCJfYmxhbmtcIlxuICAgICAgICAgICAgcmVsPVwibm9vcGVuZXIgbm9yZWZlcnJlclwiXG4gICAgICAgICAgPlxuICAgICAgICAgICAgUmVkdXhcbiAgICAgICAgICA8L2E+XG4gICAgICAgICAgPHNwYW4+LCA8L3NwYW4+XG4gICAgICAgICAgPGFcbiAgICAgICAgICAgIGNsYXNzTmFtZT17c3R5bGVzLmxpbmt9XG4gICAgICAgICAgICBocmVmPVwiaHR0cHM6Ly9yZWR1eC10b29sa2l0LmpzLm9yZy9cIlxuICAgICAgICAgICAgdGFyZ2V0PVwiX2JsYW5rXCJcbiAgICAgICAgICAgIHJlbD1cIm5vb3BlbmVyIG5vcmVmZXJyZXJcIlxuICAgICAgICAgID5cbiAgICAgICAgICAgIFJlZHV4IFRvb2xraXRcbiAgICAgICAgICA8L2E+XG4gICAgICAgICAgLDxzcGFuPiBhbmQgPC9zcGFuPlxuICAgICAgICAgIDxhXG4gICAgICAgICAgICBjbGFzc05hbWU9e3N0eWxlcy5saW5rfVxuICAgICAgICAgICAgaHJlZj1cImh0dHBzOi8vcmVhY3QtcmVkdXguanMub3JnL1wiXG4gICAgICAgICAgICB0YXJnZXQ9XCJfYmxhbmtcIlxuICAgICAgICAgICAgcmVsPVwibm9vcGVuZXIgbm9yZWZlcnJlclwiXG4gICAgICAgICAgPlxuICAgICAgICAgICAgUmVhY3QgUmVkdXhcbiAgICAgICAgICA8L2E+XG4gICAgICAgIDwvc3Bhbj5cbiAgICAgIDwvaGVhZGVyPlxuICAgIDwvZGl2PlxuICApXG59XG5cbmV4cG9ydCBkZWZhdWx0IEluZGV4UGFnZVxuIiwiLy8gRXhwb3J0c1xubW9kdWxlLmV4cG9ydHMgPSB7XG5cdFwicm93XCI6IFwiQ291bnRlcl9yb3dfXzJvd1l5XCIsXG5cdFwidmFsdWVcIjogXCJDb3VudGVyX3ZhbHVlX18xOVp0Z1wiLFxuXHRcImJ1dHRvblwiOiBcIkNvdW50ZXJfYnV0dG9uX19CdEpfd1wiLFxuXHRcInRleHRib3hcIjogXCJDb3VudGVyX3RleHRib3hfXzFsb2xzXCIsXG5cdFwiYXN5bmNCdXR0b25cIjogXCJDb3VudGVyX2FzeW5jQnV0dG9uX18xYkI3RCBDb3VudGVyX2J1dHRvbl9fQnRKX3dcIlxufTtcbiIsIi8vIEV4cG9ydHNcbm1vZHVsZS5leHBvcnRzID0ge1xuXHRcImNvbnRhaW5lclwiOiBcIkhvbWVfY29udGFpbmVyX19zRGV4T1wiLFxuXHRcImxvZ29cIjogXCJIb21lX2xvZ29fX3F4WkpTXCIsXG5cdFwiaGVhZGVyXCI6IFwiSG9tZV9oZWFkZXJfXzFydW5oXCIsXG5cdFwibGlua1wiOiBcIkhvbWVfbGlua19fM2Q1VzRcIixcblx0XCJsb2dvLWZsb2F0XCI6IFwiSG9tZV9sb2dvLWZsb2F0X18xdUxxTVwiXG59O1xuIiwibW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwiQHJlZHV4anMvdG9vbGtpdFwiKTsiLCJtb2R1bGUuZXhwb3J0cyA9IHJlcXVpcmUoXCJuZXh0L2hlYWRcIik7IiwibW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwicmVhY3RcIik7IiwibW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwicmVhY3QtcmVkdXhcIik7IiwibW9kdWxlLmV4cG9ydHMgPSByZXF1aXJlKFwicmVhY3QvanN4LWRldi1ydW50aW1lXCIpOyJdLCJuYW1lcyI6WyJ1c2VFZmZlY3QiLCJ1c2VSZWYiLCJ1c2VEaXNwYXRjaCIsInVzZVNlbGVjdG9yIiwidXNlRm9ybSIsImRlZmF1bHRWYWx1ZXMiLCJoYW5kbGVyIiwiZXZlbnQiLCJwcmV2ZW50RGVmYXVsdCIsInBlcnNpc3QiLCJmb3JtIiwidGFyZ2V0IiwiZWxlbWVudHMiLCJBcnJheSIsImZyb20iLCJkYXRhIiwiZmlsdGVyIiwiZWxlbWVudCIsImhhc0F0dHJpYnV0ZSIsInJlZHVjZSIsIm9iamVjdCIsImdldEF0dHJpYnV0ZSIsInZhbHVlIiwicmVzZXQiLCJ1c2VJbnRlcnZhbCIsImNhbGxiYWNrIiwiZGVsYXkiLCJzYXZlZENhbGxiYWNrIiwiY3VycmVudCIsImFyZ3MiLCJpZCIsInNldEludGVydmFsIiwiY2xlYXJJbnRlcnZhbCIsInVzZUFwcERpc3BhdGNoIiwidXNlQXBwU2VsZWN0b3IiLCJ1c2VTdGF0ZSIsImRlY3JlbWVudCIsImluY3JlbWVudCIsImluY3JlbWVudEJ5QW1vdW50IiwiaW5jcmVtZW50QXN5bmMiLCJpbmNyZW1lbnRJZk9kZCIsInNlbGVjdENvdW50Iiwic3R5bGVzIiwiQ291bnRlciIsImRpc3BhdGNoIiwiY291bnQiLCJpbmNyZW1lbnRBbW91bnQiLCJzZXRJbmNyZW1lbnRBbW91bnQiLCJpbmNyZW1lbnRWYWx1ZSIsIk51bWJlciIsInJvdyIsImJ1dHRvbiIsInRleHRib3giLCJlIiwiYXN5bmNCdXR0b24iLCJmZXRjaENvdW50IiwiYW1vdW50IiwicmVzcG9uc2UiLCJmZXRjaCIsIm1ldGhvZCIsImhlYWRlcnMiLCJib2R5IiwiSlNPTiIsInN0cmluZ2lmeSIsInJlc3VsdCIsImpzb24iLCJjcmVhdGVBc3luY1RodW5rIiwiY3JlYXRlU2xpY2UiLCJpbml0aWFsU3RhdGUiLCJzdGF0dXMiLCJjb3VudGVyU2xpY2UiLCJuYW1lIiwicmVkdWNlcnMiLCJzdGF0ZSIsImFjdGlvbiIsInBheWxvYWQiLCJleHRyYVJlZHVjZXJzIiwiYnVpbGRlciIsImFkZENhc2UiLCJwZW5kaW5nIiwiZnVsZmlsbGVkIiwiYWN0aW9ucyIsImNvdW50ZXIiLCJnZXRTdGF0ZSIsImN1cnJlbnRWYWx1ZSIsInJlZHVjZXIiLCJIZWFkIiwiSW5kZXhQYWdlIiwiY29udGFpbmVyIiwiaGVhZGVyIiwibG9nbyIsImxpbmsiXSwic291cmNlUm9vdCI6IiJ9
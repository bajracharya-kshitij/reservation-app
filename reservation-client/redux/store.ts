import { applyMiddleware, combineReducers, createStore } from 'redux';
import { createWrapper } from 'next-redux-wrapper';
import thunkMiddleware from 'redux-thunk';
import authReducer from "./auth.slice";

export const rootReducer = combineReducers({
  auth: authReducer
});

export type AppState = ReturnType<typeof rootReducer>;

const bindMiddleware = (middleware: any) => {
  if (process.env.NODE_ENV !== 'production') {
    const { composeWithDevTools } = require('redux-devtools-extension')
    return composeWithDevTools(applyMiddleware(...middleware))
  }
  return applyMiddleware(...middleware)
}


// create a makeStore function
const makeStore = () => {
  return createStore(rootReducer, bindMiddleware([thunkMiddleware]));
};

// export an assembled wrapper
export const wrapper = createWrapper(makeStore, { debug: false });



import './App.css';
import Header from './components/Header.js';
import LoginForm from './components/LoginForm.js'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
function App() {
return (
    <Router>
    <div className="App">
      <Header/>
        <div className="container d-flex align-items-center flex-column">
          <Switch>
            <Route path="/login" exact={true}>
              <LoginForm />
            </Route>
            <Route path="/" exact={true}>
              
            </Route>
          </Switch>
       </div>
   </div>
  </Router>
  )  
}

export default App;

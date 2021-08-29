import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import {ScoreBoard} from './components/scoreBoard'

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route path="/" exact>
            
            <ScoreBoard/>
          </Route>
        </Switch>
      </Router>
    </>
  );
}

export default App;
import logo from './logo.svg';
import './App.css';

function App() {
  return (  // JSX: 자바스크립트 내부에서 HTML코드를 사용할 수 있는 문법 (Babel 라이브러리가 빌드 시 자바스크립트로 번역해줌)
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App; // 해당 컴포넌트를 다른 컴포넌트에서 사용할 수 있음

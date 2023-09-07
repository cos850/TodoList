/**
 * SPA (Single Page Application)
 * : 한 번 웹 페이지를 로딩하면 사용자가 임의로 새로고침하지 않는 이상 페이지를 새로 로딩하지 않는 애플리케이션.
 * 
 * SPA 랜더링 순서
 * 1. index.html 파싱&렌더링 (npm start 실행 시 하단에 bundle.js 생성. 해당 js 내부에 index.js 포함)
 * 2. index.js 실행 
 * 3. root 엘리먼트 하위 엘리먼트 렌더링
 * 
 */

import React from 'react'; 
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';  // import를 사용해 App 컴포넌트 불러옴. "<컴포넌트이름 />" 와 같이 사용
import reportWebVitals from './reportWebVitals';

/**
 * ReactDOM.render()
 * : ReactDOM이 매개변수로 넘긴 컴포넌트를 root 엘리먼트에 랜더링함
 * => 서버에 새 HTML 페이지를 요청하지 않고 자바스크립트가 동적으로 root 하위 엘리먼트를 수정 (클라이언트-사이드 랜더링)
 */
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

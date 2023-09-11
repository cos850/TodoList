import React from 'react';
import Todo from './Todo';
import './App.css';
import { Container, List, Paper } from '@mui/material';
import AddTodo from './AddTodo';
import { call } from './ApiService';

class App extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      items: []
    };
  }

  /** 
   * componentDidMount
   * : 렌더링의 맨 처음, ReactDOM 트리가 없는 상태에서 생성자와 render함수의 호출하여 
   *   자신의 DOM 트리를 구성(mounting)한 뒤 호출되는 함수.
   *   
   * 생성자에서는 컴포넌트의 프로퍼티(ex setState 등)가 준비되지 않아 해당 프로퍼티를 사용할 수 없으므로 
   * 랜더링이 끝난 후 실행되는 함수인 componetDidMount() 에서 Http요청을 수행한다.
   * 
   */
  componentDidMount(){
    call("/todo", "GET", null).then((response)=>{
      this.setState({items: response.data});
    });
  };

  add = (item) =>{
    call("/todo", "POST", item).then((response) => {
      this.setState({items: response.data});
    });
  };

  delete = (item) => {
    call("/todo", "DELETE", item).then((response)=>{
      this.setState({items: response.data});
    });
  };

  render() {
    // JSX: 자바스크립트 내부에서 HTML코드를 사용할 수 있는 문법 (Babel 라이브러리가 빌드 시 자바스크립트로 번역해줌)
    // Todo와 같은 컴포넌트는 여러번 재사용이 가능하다
    var todoItems = this.state.items.length > 0 && (
      <Paper style={{margin: 16}}>
        <List>
          {this.state.items.map((item, idx)=>(
            <Todo item={item} key={item.id} delete={this.delete} />
          ))}
        </List>
      </Paper>
    );

    return (
      <div className="App">
        <Container maxWidth="md">
          <AddTodo add={this.add}/>
          <div className='TodoList'>{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App; // 해당 컴포넌트를 다른 컴포넌트에서 사용할 수 있음

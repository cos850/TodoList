import { DeleteOutlined } from '@mui/icons-material';
import { Checkbox, IconButton, InputBase, ListItem, ListItemSecondaryAction, ListItemText } from '@mui/material';
import React from 'react';

class Todo extends React.Component {
    constructor(props){
        super(props);
        /** 
         * state
         * : 리액트가 관리하는 오브젝트. 추후 변경할 수 있는 변수를 state오브젝트에서 관리.
         * 자바스크립트 내에서 변경한 변수의 값을 HTML에 다시 랜더링하기 위함.
         * 
         * * JSX에서 자바스크립트 변수를 사용하려면 변수를 {}로 묶어줌
         */
        this.state = {item: props.item, readOnly: true};
        this.delete = props.delete;
    }

    checkboxEventHandler = (e) => {
        console.log('checkboxEventHandler ', this.state.item.done)
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({item: thisItem});
    }

    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item: thisItem});
    }

    enterKeyEventHandler = (e) => {
        if(e.key === "Enter")
            this.setState({readOnly: true});
    }

    offReadOnlyMode = () => {
        console.log("Event! ", this.state.readOnly);
        this.setState({readOnly: false}, ()=>{
            console.log("ReadOnly? ", this.state.readOnly);
        });
    }

    deleteEventHandler = () => {
        this.delete(this.state.item);
    }

    render() {
        const item = this.state.item; 
        return (
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEventHandler} />
                <ListItemText>
                    <InputBase 
                        inputProps={{"aria-label": "naked", readOnly: this.state.readOnly}}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        onClick={this.offReadOnlyMode}
                        onChange={this.editEventHandler}
                        onKeyDown={this.enterKeyEventHandler}
                    />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label='Delete Todo' onClick={this.deleteEventHandler}>
                        <DeleteOutlined />
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

export default Todo
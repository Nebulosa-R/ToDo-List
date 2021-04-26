import React from 'react';
import './App.css';
import { FolderList } from './FolderList';
import Container from '@material-ui/core/Container';

class App extends React.Component<{}, any>{

  constructor(props: any){
    super(props);
    this.state = {
      folders: [],
      isLoading: false
    };
  }

  render() { 
    return (
      <Container maxWidth="sm">
        <FolderList/>
      </Container>   
    );
  }
}

export default App;
import React, { Component } from 'react';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
//import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
//import FormGroup from '@material-ui/core/FormGroup';
//import FormControlLabel from '@material-ui/core/FormControlLabel';
//import Checkbox from '@material-ui/core/Checkbox';
//import Grid from '@material-ui/core/Grid';
import FolderIcon from '@material-ui/icons/Folder';
import DeleteIcon from '@material-ui/icons/Delete';
import AppBar from '@material-ui/core/AppBar';
import Typography from '@material-ui/core/Typography';
import Toolbar from '@material-ui/core/Toolbar';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
//import FormControl from '@material-ui/core/FormControl';
//import Input from '@material-ui/core/Input';
//import InputLabel from '@material-ui/core/InputLabel';
import { BrowserRouter, Route, Link, withRouter } from 'react-router-dom';
//import Link from 'react-router';
//import Link from '@material-ui/core/Link';

//import {ItemList} from './ItemList';
import FolderDetail from './FolderDetail';
import './FolderList.css';


interface FolderSate{
    folders: any[],
    isLoading: boolean,
    folderName: string
}

export class FolderList extends Component<{}, FolderSate> {
  constructor(props: any) {
    super(props);
    
    this.state = {
      folders: [],
      isLoading: false,

      folderName: ""
    };

    this.handleSubmit =  this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleRemove = this.handleRemove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('folders/list')
      .then(response => response.json())
      .then(data => this.setState({folders: data, isLoading: false}));

  }
  
  handleChange(target: any){
      //console.log( target.currentTarget.value);
      //console.log(target.currentTarget.name);
      this.setState({
        ['folderName']: target.currentTarget.value
      });

  }

  async handleSubmit(event: any){
      if(this.state.folderName !== ''){
        console.log("adding a new folder");
        const folder = {"folderName": this.state.folderName};

        await fetch('folders/add', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(folder),
          })
            .then((response) => response.json())
            .then((data) => {
                console.log(JSON.stringify(data));
                this.state.folders.push(data);
                let updatedFolders = this.state.folders;
                this.setState({
                  ['folders']: updatedFolders
                });
              })
            .catch((error)=>{
                console.log('Error:', error);
            });  
      
          
      }
      this.setState({
        ['folderName']: ''
      });
    }

   async handleRemove(id: any){
       console.log('remode: ', id);
        await fetch(`folders/delete/${id}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
      }).then(() => {
          let updatedFolders = [...this.state.folders].filter(folder => folder.id !== id);
          this.setState({folders: updatedFolders});
        });
    }

  render() {
    const {folders, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div >
            <AppBar className="Bar" position="static">
                <Toolbar>
                    <Typography variant="h6">
                        Folders
                    </Typography>
                </Toolbar>
            </AppBar>
            <List >
                {folders.map((folder: any) =>
                    <ListItem key={folder.id}>
                        <ListItemAvatar>
                            <Avatar>
                            <FolderIcon />
                            </Avatar>
                        </ListItemAvatar>

                        <ListItemText
                            primary= {folder.folderName}
                        />
                        <Link
                            to={"/"+folder.id+"/items"}
                        >
                            View items
                        </Link>
                        <ListItemSecondaryAction>
                            
                            <IconButton edge="end" aria-label="delete"
                                onClick={() => this.handleRemove(folder.id)}
                            >
                            <DeleteIcon />
                            </IconButton>
                        </ListItemSecondaryAction>
                    </ListItem>
                )}
            </List>
            <form >           
                <TextField className="AddText" id="outlined-basic" 
                    label="New Folder" variant="outlined" 
                    name="folderName" required={true}
                    value ={this.state.folderName}
                    onChange={ this.handleChange } 
                    />
                <Button 
                        className="AddButton"  
                        variant="contained"
                        onClick={this.handleSubmit}
                        >
                        Add
                </Button>
            </form>
            <BrowserRouter>
            <Route exact path="/:id/items" component={FolderDetail} />
            </BrowserRouter>
          </div>
        
    );
  }
}

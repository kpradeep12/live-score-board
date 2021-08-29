import React from "react";
import { Button, Grid, Modal } from "@material-ui/core";
import LiveTvIcon from '@material-ui/icons/LiveTv';
import SportsEsportsIcon from '@material-ui/icons/SportsEsports';
import TransitEnterexitIcon from '@material-ui/icons/TransitEnterexit';
import { makeStyles } from '@material-ui/core/styles';
import { Box } from "@material-ui/core";
import { Typography } from "@material-ui/core";
import { AppBar, Toolbar, IconButton } from "@material-ui/core";
import MenuIcon from '@material-ui/icons/Menu';
import theme from "../theme";
import UndoIcon from '@material-ui/icons/Undo';
import PlayArrowIcon from '@material-ui/icons/PlayArrow';
import SockJsClient from 'react-stomp';
import Websocket from 'react-websocket';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
      },
      menuButton: {
        marginRight: theme.spacing(2),
      },
      title: {
        flexGrow: 1,
      },
}));

export class ScoreBoard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            redScore: 0,
            blueScore: 0
        }
    }

    
    
    componentDidMount() {
        axios.get("/score").then(
            result => {
                this.setState({
                    redScore: result.data.redScore,
                    blueScore: result.data.blueScore
                });
            },
            error => {
                console.log('error in getting score');
            }
            );
    
    }

    restartGame() {
        axios.put("/score/reset").then(
            result => {},
            error => {
                console.log('error in reset game');
            }
            );
    }

    undoScore() {
        axios.put("/score/undo").then(
            result => {},
            error => {
                console.log('error in undo');
            }
            );
    }
    
    onMessageReceive = (msg, topic) => {
        console.log(msg);
        this.setState({
            redScore: msg.redScore,
            blueScore: msg.blueScore
        });
        /*this.setState(prevState => ({
          messages: [...prevState.messages, msg]
        }));*/
      }

    render() {
        return(
        <>
            <SockJsClient url={"/publish-score"} topics={['/topic/score']}
            onMessage={this.onMessageReceive}
            ref={ (client) => { this.clientRef = client }} />

            <div style={{flexGrow: '1'}}>
                <AppBar position="static">
                    <Toolbar>
                        <IconButton edge="start" color="inherit" aria-label="menu">
                        
                        </IconButton>
                        <Typography variant="h6" style={{flexGrow: '1'}}>
                        Live Score Board
                        </Typography>
                        <Button color="inherit" onClick={() => {this.restartGame()}}><PlayArrowIcon/>Start new game</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <Button color="inherit" onClick={() => {this.undoScore()}}><UndoIcon/>Undo</Button>
                        
                    </Toolbar>
                </AppBar>
            </div>
            
        <Grid container direction="row"
                alignItems="center"
                justify="center"
                style={{ minHeight: '90vh' }}>
            <Grid item style={{ minHeight: '90vh', backgroundColor:'red', color: 'white', textAlign:'center'}} xs={6}>
                <Box component="div">
                    <Typography variant="h1" component="h2"  style={{fontSize:'45vw'}}>
                        {this.state.redScore}
                    </Typography>
                </Box>
            </Grid>

            <Grid item style={{ minHeight: '90vh', backgroundColor:'blue', color: 'white', textAlign:'center'}} xs={6}>
                <Box component="div">
                    <Typography variant="h1" component="h2"  style={{fontSize:'45vw'}}>
                        {this.state.blueScore}
                    </Typography>
                </Box>
            </Grid>
        </Grid>

        </>
        )
    }
}

export default ScoreBoard;
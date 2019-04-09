import React from "react";
import {withStyles} from "@material-ui/core";
import PropTypes from "prop-types";
import Modal from "@material-ui/core/Modal";

export function getModalStyle() {
    const top = 50;
    const left = 50;

    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
        overflowY: 'auto',
        maxHeight: "90%",
    };
}

const styles = theme => ({
    paper: {
        position: 'absolute',
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: "30px 80px 40px",
        outline: "none",
        width: 450,
        borderRadius: 10,
    },
    root: {
        width: '100%',
        backgroundColor: theme.palette.background.paper,
    },
    bot: {
        color: "#b0b0b0!important",
    },
    flex: {},
    container: {},
    menuButton: {},
    loader: {
        position: 'absolute',
        left: '50%',
        marginLeft: '-35px',
        top: '40%',
        zIndex: 9999
    },
    buttonHolder: {
        marginTop: 40,
        display: "flex",
        justifyContent: "flex-end",
    },
});

class CustomizedModal extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
                <Modal
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                    open={this.props.open}
                    disableBackdropClick={this.props.disableClose}
                    onClose={this.props.onClose}
                    style={{overflowY: 'hidden'}}
                >
                    <div style={getModalStyle()} className={classes.paper}>
                        {this.props.children}
                    </div>
                </Modal>
            </div>
        )
    }
}

CustomizedModal.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CustomizedModal);

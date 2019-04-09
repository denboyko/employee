import React from "react";
import PropTypes from "prop-types";
import {withStyles} from "@material-ui/core/styles";
import IconButton from "@material-ui/core/IconButton";
import KeyboardArrowRight from "@material-ui/core/es/internal/svg-icons/KeyboardArrowRight";
import KeyboardArrowLeft from "@material-ui/core/es/internal/svg-icons/KeyboardArrowLeft";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";

const actionsStyles = theme => ({
    root: {
        flexShrink: 0,
        color: theme.palette.text.secondary,
        marginLeft: theme.spacing.unit * 2.5,
    },
    page: {
        fontSize: "14px",
    }
});

class TablePaginationActions extends React.Component {
    handleFirstPageButtonClick = event => {
        this.props.onChangePage(event, 0);
    };

    handleBackButtonClick = event => {
        this.props.onChangePage(event, this.props.page - 1);
    };

    handleNextButtonClick = event => {
        this.props.onChangePage(event, this.props.page + 1);
    };

    handleLastPageButtonClick = event => {
        this.props.onChangePage(
            event,
            Math.max(0, Math.ceil(this.props.count / this.props.rowsPerPage) - 1),
        );
    };

    render() {
        const {classes, count, page, rowsPerPage, theme} = this.props;

        return (
            <div className={classes.root}>
                {count > 0 && <span style={{marginRight: 30}}>{page * rowsPerPage +1} - {count > (page + 1) * rowsPerPage ? (page + 1) * rowsPerPage : count } of {count}</span>}

                <IconButton
                    color="primary"
                    onClick={this.handleFirstPageButtonClick}
                    disabled={page === 0}
                    aria-label="First Page"
                >
                    {theme.direction === 'rtl' ? <LastPage /> : <FirstPage />}
                </IconButton>
                <IconButton
                    color="primary"
                    onClick={this.handleBackButtonClick}
                    disabled={page === 0}
                    aria-label="Previous Page"
                >
                    {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
                </IconButton>
                {page > 2 && "..."}
                {page > 1 && <IconButton
                    color="primary"
                    className={classes.page}
                    onClick={(event) => this.props.onChangePage(event, this.props.page - 2)}
                    aria-label="current"
                >
                    {this.props.page - 1}
                </IconButton>}
                {page > 0 && <IconButton
                    color="primary"
                    className={classes.page}
                    onClick={(event) => this.props.onChangePage(event, this.props.page - 1)}
                    aria-label="current"
                >
                    {this.props.page}
                </IconButton>}
                <IconButton
                    color="primary"
                    aria-label="current"
                    className={classes.page}
                    style={{fontWeight: "bold"}}
                >
                    {this.props.page + 1}
                </IconButton>
                {page < Math.ceil(count / rowsPerPage) - 1 && <IconButton
                    color="primary"
                    className={classes.page}
                    onClick={(event) => this.props.onChangePage(event, this.props.page + 1)}
                    aria-label="current"
                >
                    {this.props.page + 2}
                </IconButton>}
                {page + 1 < Math.ceil(count / rowsPerPage) - 1 && <IconButton
                    color="primary"
                    className={classes.page}
                    onClick={(event) => this.props.onChangePage(event, this.props.page + 2)}
                    aria-label="current"
                >
                    {this.props.page + 3}
                </IconButton>}
                {page + 1 < Math.ceil(count / rowsPerPage) - 1 && "..."}
                <IconButton
                    color="primary"
                    onClick={this.handleNextButtonClick}
                    disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                    aria-label="Next Page"
                >
                    {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
                </IconButton>
                <IconButton
                    color="primary"
                    onClick={this.handleLastPageButtonClick}
                    disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                    aria-label="Last Page"
                >
                    {theme.direction === 'rtl' ? <FirstPage/> : <LastPage />}
                </IconButton>
            </div>
        );
    }
}

TablePaginationActions.propTypes = {
    classes: PropTypes.object.isRequired,
    count: PropTypes.number.isRequired,
    onChangePage: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
    theme: PropTypes.object.isRequired,
};

export default withStyles(actionsStyles, {withTheme: true})(
    TablePaginationActions,
);
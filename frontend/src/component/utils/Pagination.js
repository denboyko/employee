import React from "react";
import PropTypes from "prop-types";
import TablePagination from "@material-ui/core/TablePagination";
import withStyles from "@material-ui/core/styles/withStyles";
import TablePaginationActionsWrapped from "./TablePaginationActionsWrapped";

const styles = theme => ({
    caption: {
        display: "none!important",
    }
});

class Pagination extends React.Component {

    render(){
        const {classes} = this.props;
        return (
            <TablePagination
                count={this.props.count}
                rowsPerPage={this.props.rowsPerPage}
                rowsPerPageOptions={[5, 10, 25]}
                page={this.props.page}
                classes={{caption: classes.caption}}
                onChangePage={this.props.onChangePage}
                // onChangeRowsPerPage={this.handleChangeRowsPerPage}
                ActionsComponent={TablePaginationActionsWrapped}
                SelectProps={{
                    style: {
                        display: "none",
                    }
                }}
                labelDisplayedRows={this.props.labelDisplayedRows}
            />
        )
    }
}

Pagination.propTypes = {
    classes: PropTypes.object,
    count: PropTypes.number.isRequired,
    onChangePage: PropTypes.func,
    page: PropTypes.number,
    rowsPerPage: PropTypes.number,
};

export default withStyles(styles)(Pagination);
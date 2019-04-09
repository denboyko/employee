import React from "react";
import withStyles from "@material-ui/core/styles/withStyles";
import Button from "@material-ui/core/Button";
import {deleteEmployee} from "../api/Employee";
import {getAllDepartments} from "../api/Department";

const styles = (theme) => ({
    table: {
        border: "1px solid grey",
        margin: "20px 0",
    },
    tableRow: {
        display: "flex",
        fontSize: "14px",
        borderBottom: "1px solid grey",
    },
    column: {
        flexGrow: 1,
        textAlign: "center",
        flexBasis: 0,
        borderRight: "1px solid",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    }
});

class EmployeeTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            departments: [],
        }
    }

    componentDidMount() {
        getAllDepartments((departments) => this.setState({departments}));

    }

    deleteEmployee(id) {
        deleteEmployee(id, this.props.reload);
    }

    view(id) {
        this.props.handleView(id);
    }

    edit(id) {
        this.props.handleEdit(id);
    }

    getDepartment(id) {
        let dep = this.state.departments.find((dep) => dep.id === id);
        if (dep) {
            return dep.name;
        } else
            return "DepId: " + id;
    }

    render() {
        const {classes} = this.props;
        return (
            <div className={classes.table}>
                <div className={classes.tableRow}>
                    <div className={classes.column}></div>
                    <div className={classes.column}></div>
                    <div className={classes.column}>Id</div>
                    <div className={classes.column} style={{flexGrow: 2}}>Name</div>
                    <div className={classes.column}>Active</div>
                    <div className={classes.column}>Department</div>
                    <div className={classes.column}></div>
                </div>
                {this.props.employees.map((employee) =>
                    <div key={employee.id} className={classes.tableRow}>
                        <div className={classes.column}>
                            <Button onClick={() => this.view(employee.id)}>View</Button>
                        </div>
                        <div className={classes.column}>
                            <Button onClick={() => this.edit(employee.id)}>Edit</Button>
                        </div>
                        <div className={classes.column}>{employee.id}</div>
                        <div className={classes.column} style={{flexGrow: 2}}>{employee.name}</div>
                        <div className={classes.column}>{employee.active ? "Yes" : "No"}</div>
                        <div className={classes.column}>{this.getDepartment(employee.depId)}</div>
                        <div className={classes.column}>
                            <Button onClick={() => this.deleteEmployee(employee.id)}>Delete</Button>
                        </div>
                    </div>
                )}
            </div>
        )
    }
}

export default withStyles(styles, {withTheme: true})(EmployeeTable);
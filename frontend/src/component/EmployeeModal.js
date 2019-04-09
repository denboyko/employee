import React from "react";
import withStyles from "@material-ui/core/styles/withStyles";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";
import Checkbox from "@material-ui/core/Checkbox";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import {createEmployee, getEmployee, updateEmployee} from "../api/Employee";
import {getAllDepartments} from "../api/Department";

const styles = theme => ({
    buttonHolder: {
        display: "flex",
        justifyContent: "space-around",
        margin: 20,
    }
});

class EmployeeModal extends React.Component {

    handleChange = (event) => {
        this.setState({checked: event.target.checked});
    };

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            checked: true,
            departments: [],
            depId: 1,
        }
    }

    componentDidMount() {
        getAllDepartments((departments) => this.setState({departments}));
        if (this.props.employeeId) {
            getEmployee(this.props.employeeId, (name, active, depId) => this.setState({name, active, depId}))
        }
    }


    createNewEmployee() {
        let employee = {
            name: this.state.name,
            active: this.state.checked,
            depId: this.state.depId,
        };
        if (this.props.employeeId) {
            employee.id = this.props.employeeId;
            updateEmployee(employee, this.props.onCreate);
        } else {
            createEmployee(employee, this.props.onCreate);
        }
    }

    handleSelect(event) {
        this.setState({depId: event.target.value});
    }

    render() {
        const {classes} = this.props;

        return (
            <div style={{
                display: "flex",
                flexDirection: "column"
            }}>
                <h3>{this.props.employeeId ? "Update the " : "Create a new "} Employee</h3>

                <Input value={this.state.name}
                       onChange={(event) => this.setState({name: event.target.value})}
                       disabled={this.props.readOnly}
                       placeholder="Employee name"/>

                <FormControlLabel
                    control={
                        <Checkbox
                            checked={this.state.checked}
                            onChange={this.handleChange}
                            color="primary"
                        />
                    }
                    label="Active"
                    disabled={this.props.readOnly}
                />
                <select value={this.state.depId}
                        disabled={this.props.readOnly}
                        onChange={(event) => this.handleSelect(event)}>
                    {this.state.departments.map((dep) =>
                        <option value={dep.id} key={dep.id}>{dep.name}</option>
                    )}
                </select>
                <div className={classes.buttonHolder}>
                    {!this.props.readOnly &&
                    <Button variant="contained"
                            color="primary"
                            onClick={() => this.createNewEmployee()}>
                        {this.props.employeeId ? "Update" : "Create"}
                    </Button>}
                    <Button variant="contained" onClick={this.props.onClose}>Close</Button>
                </div>
            </div>

        )
    }
}

export default withStyles(styles, {withTheme: true})(EmployeeModal);
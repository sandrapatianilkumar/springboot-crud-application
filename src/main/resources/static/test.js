import ReactDOM from "react-dom";
import React, { Component } from "react";

class CoronaVirus extends Component {
  constructor(props) {
    super(props);
    this.state = {
      list: []
    };
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    fetch("http://localhost:9090/test")
      .then(response => response.json())
      .then(data => {
        this.setState({
          list: data
        });
      });
  }

  handleChange(e) {
    fetch("http://localhost:9090/test")
      .then(response => response.json())
      .then(data => {
        this.setState({
          list: data
        });
      });
  }

  render() {
    return (
      <div className="container">
        <h1>Corona virus</h1>
        {this.state.list.map((item, index) => {
          return (
            <table class="table table-strip  table-hover">
              <thead>
                <tr>
                  <th>State</th>
                  <th>Country</th>
                  <th>TotalCases</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>{item.state}</td>
                  <td>{item.country}</td>
                  <td>{item.totalCases}</td>
                </tr>
              </tbody>
            </table>
          );
        })}
        <button onClick={() => this.handleChange()}>Refresh</button>
      </div>
    );
  }
}
ReactDOM.render(<CoronaVirus />, document.getElementById("root"));

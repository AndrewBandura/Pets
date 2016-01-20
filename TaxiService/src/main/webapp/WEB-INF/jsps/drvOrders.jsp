<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!doctype html>
<html ng-app="app">
    <head>
        <title>Driver's dashboard</title>
        <div ng-controller="DrvCtrl">
            <p>{{header}}</p>
        </div>
        <script src="js/angular.js"></script>
        <%--<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular.js"></script>--%>
        <script src="js/drivers.js"></script>
        <%--<script src="js/ajaxa.js"></script>--%>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.18/angular-resource.js"></script>
    </head>

<body>
    <table>
        <tr>
            <td>
                <a href="/drvOrders" color:blue> Show all </a>
            </td>
            <td>
                <a href="/orderFilter" color:blue> Filter by.. </a>
            </td>
        </tr>
    </table>

    <style>
        .odd {
            background-color: #fafafa;
        }
        .even {
            background-color: silver
        }
    </style>

    <br>

    <div ng-controller="DrvCtrl">
        <table align="left" cellspacing="2" cellpadding="5">
            <tr>
                <td>
                    <button ng-click="update()">update</button>
                    <button ng-click="getAll()">get all</button>
                </td>
                <td>
                    <p>{{greeting}}</p>
                </td>
            </tr>
        </table>
        <br>
        <table align="left" cellspacing="2" cellpadding="5">
            <thead>
            <tr>
                <th>#</th>
                <th>id</th>
                <th>Client</th>
                <th>Phone</th>
                <th>Address from</th>
                <th>Address to</th>
                <th>Amount</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="order in orders" ng-class="$odd ? 'odd' : 'even'">
                <td>{{$index + 1}}</td>
                <td>{{order[0]}}</td>
                <td>{{order[1]}}</td>
                <td>{{order[2]}}</td>
                <td>{{order[3]}}</td>
                <td>{{order[4]}}</td>
                <td>{{order[5]}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
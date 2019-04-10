# employee

Implement SPA with authentication
page and one more page + RestAPI on backend for CRUD operations on one table.

DB:

tblEmployees:
empID (identity field)
empName
empActive
emp_dpID – foreign key to tblDepartments

tblDepartments:
dpID
dpName




+ it should have one “Search” feature:

And it should search ONLY with “Start with” criteria! (not include!).

!!!NOTE!!!
UI beauty is NOT considered at all. So, html/css doesn’t matter!

Conditions:
1. Table theoretically can have more fields and a lot of data.
2. So, paging is essential part of this test task.
3. Please use MySQL for test task.
4. Don’t use Hibernate

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  

  constructor(private httpClient: HttpClient) { }
  
  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>('http://localhost:8080/api/v1/employees');
  }

  createEmployee(employee:Employee) : Observable<Object>{
    return this.httpClient.post('http://localhost:8080/api/v1/employees',employee);
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>('http://localhost:8080/api/v1/employees/'+id);
  }

  updateEmployee(id: number, employee: Employee): Observable<Object>{
    return this.httpClient.put('http://localhost:8080/api/v1/employees/'+id, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete('http://localhost:8080/api/v1/employees/'+id);
  }


}

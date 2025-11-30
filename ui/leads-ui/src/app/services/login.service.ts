import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Session } from "./session";

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  private sessionStorage = inject(Session);
  private readonly fakeAuthUrl = 'http://localhost:8080/api/fake-store';
  private readonly dbAuthUrl = 'http://localhost:8080/api/public';
  private readonly authToken: string = 'AUTH_TOKEN';
  private readonly loginSource: string = 'LOGIN_SOURCE';
  private isLoggedIn = false;
  private user: any | null = null;

  constructor(private http: HttpClient) { }
  login(username: string, password: string, loginSource: string) {
    this.isLoggedIn = true;
    if (loginSource === 'fake') {
      return this.http.post<any>(`${this.fakeAuthUrl}/login`, { username, password });
    } else {
      return this.http.post<any>(`${this.dbAuthUrl}/login`, { username, password });
    }
  }

  saveSession(token: string, loginSource: string): void {
    this.sessionStorage.setItem(this.authToken, token);
    this.sessionStorage.setItem(this.loginSource, loginSource);
  }
  clearSession(): void {
    this.sessionStorage.clear();
  }

  isAuthenticated(): boolean {
    return this.sessionStorage.getItem(this.authToken) !== null;
  }

  getToken(): string | null{
    return this.sessionStorage.getItem(this.authToken);
  }

  logout(): void {
    this.isLoggedIn = false;
    this.clearSession();
  }
}

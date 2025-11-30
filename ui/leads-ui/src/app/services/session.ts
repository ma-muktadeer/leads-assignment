import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Session {
  private sessionStorage = sessionStorage;

  setItem(key: string, value: string): void {
    this.sessionStorage.setItem(key, value);
  }
  getItem(key: string): string | null {
    return this.sessionStorage.getItem(key);
  }
  removeItem(key: string): void {
    this.sessionStorage.removeItem(key);
  }
  clear(): void {
    this.sessionStorage.clear();
  }

}

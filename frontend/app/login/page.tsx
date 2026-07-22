"use client";

import React, { useState } from 'react';
import Link from 'next/link';
import styles from './login.module.css';

export default function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    // TODO: Connect to backend API
    setTimeout(() => setIsLoading(false), 1500); // Mock loading
  };

  return (
    <div className={styles.container}>
      <div className={styles.leftPane}>
        <div className={styles.brand}>
          <h1>PG Connect</h1>
          <p>Streamline your PG operations seamlessly.</p>
        </div>
      </div>
      
      <div className={styles.rightPane}>
        <div className={styles.formContainer}>
          <div className={styles.header}>
            <h2>Welcome Back</h2>
            <p>Please enter your details to sign in.</p>
          </div>
          
          <form onSubmit={handleSubmit} className={styles.form}>
            <div className={styles.inputGroup}>
              <label htmlFor="email">Email address</label>
              <input 
                type="email" 
                id="email" 
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter your email"
                required 
              />
            </div>
            
            <div className={styles.inputGroup}>
              <label htmlFor="password">Password</label>
              <input 
                type="password" 
                id="password" 
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                required 
              />
            </div>
            
            <div className={styles.actions}>
              <div className={styles.remember}>
                <input type="checkbox" id="remember" />
                <label htmlFor="remember">Remember for 30 days</label>
              </div>
              <Link href="/forgot-password" className={styles.forgot}>
                Forgot password?
              </Link>
            </div>
            
            <button type="submit" className={styles.submitBtn} disabled={isLoading}>
              {isLoading ? 'Signing in...' : 'Sign in'}
            </button>
          </form>
          
          <div className={styles.footer}>
            <p>
              Don't have an account? <Link href="/register">Sign up</Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

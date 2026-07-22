import React from 'react';
import Link from 'next/link';
import { Home, Users, Building, Settings, LogOut, Coffee, Shield } from 'lucide-react';
import styles from './dashboard.module.css';

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div className={styles.container}>
      {/* Sidebar */}
      <aside className={styles.sidebar}>
        <div className={styles.sidebarHeader}>
          <h2>PG Connect</h2>
        </div>
        
        <nav className={styles.nav}>
          <Link href="/dashboard" className={`${styles.navItem} ${styles.active}`}>
            <Home size={20} />
            <span>Dashboard</span>
          </Link>
          <Link href="/dashboard/properties" className={styles.navItem}>
            <Building size={20} />
            <span>Properties</span>
          </Link>
          <Link href="/dashboard/residents" className={styles.navItem}>
            <Users size={20} />
            <span>Residents</span>
          </Link>
          <Link href="/dashboard/operations" className={styles.navItem}>
            <Coffee size={20} />
            <span>Operations</span>
          </Link>
          <Link href="/dashboard/complaints" className={styles.navItem}>
            <Shield size={20} />
            <span>Complaints</span>
          </Link>
        </nav>
        
        <div className={styles.sidebarFooter}>
          <Link href="/settings" className={styles.navItem}>
            <Settings size={20} />
            <span>Settings</span>
          </Link>
          <Link href="/login" className={styles.navItem}>
            <LogOut size={20} />
            <span>Log out</span>
          </Link>
        </div>
      </aside>

      {/* Main Content */}
      <main className={styles.mainContent}>
        <header className={styles.topbar}>
          <div className={styles.search}>
            <input type="text" placeholder="Search..." />
          </div>
          <div className={styles.profile}>
            <div className={styles.avatar}>JD</div>
            <span>John Doe</span>
          </div>
        </header>
        
        <div className={styles.content}>
          {children}
        </div>
      </main>
    </div>
  );
}

"use client";

import React, { useState } from 'react';
import { Utensils, CheckCircle, Bell } from 'lucide-react';
import styles from './operations.module.css';

export default function OperationsPage() {
  const [activeTab, setActiveTab] = useState('food');
  const [menu, setMenu] = useState({
    breakfast: 'Poha, Tea, Boiled Eggs',
    lunch: 'Roti, Dal, Paneer Sabzi, Rice',
    dinner: 'Roti, Mixed Veg, Dal Makhani',
    isBreakfastReady: true,
    isLunchReady: false,
    isDinnerReady: false,
  });

  return (
    <div className={styles.pageContainer}>
      <div className={styles.pageHeader}>
        <div>
          <h1>Operations Management</h1>
          <p>Manage daily food menu, housekeeping, and maintenance.</p>
        </div>
      </div>

      <div className={styles.tabs}>
        <button 
          className={`${styles.tabBtn} ${activeTab === 'food' ? styles.active : ''}`}
          onClick={() => setActiveTab('food')}
        >
          Food & Kitchen
        </button>
        <button 
          className={`${styles.tabBtn} ${activeTab === 'housekeeping' ? styles.active : ''}`}
          onClick={() => setActiveTab('housekeeping')}
        >
          Housekeeping
        </button>
      </div>

      {activeTab === 'food' && (
        <div className={styles.tabContent}>
          <div className={styles.card}>
            <div className={styles.cardHeader}>
              <h2>Today's Menu</h2>
              <button className={styles.outlineBtn}>Edit Menu</button>
            </div>
            
            <div className={styles.menuGrid}>
              <div className={styles.mealSection}>
                <div className={styles.mealHeader}>
                  <h3>Breakfast</h3>
                  {menu.isBreakfastReady ? (
                    <span className={styles.statusReady}><CheckCircle size={16}/> Ready</span>
                  ) : (
                    <span className={styles.statusPending}>Preparing...</span>
                  )}
                </div>
                <p>{menu.breakfast}</p>
                {!menu.isBreakfastReady && (
                  <button className={styles.notifyBtn}>
                    <Bell size={16} /> Mark Ready & Notify
                  </button>
                )}
              </div>

              <div className={styles.mealSection}>
                <div className={styles.mealHeader}>
                  <h3>Lunch</h3>
                  {menu.isLunchReady ? (
                    <span className={styles.statusReady}><CheckCircle size={16}/> Ready</span>
                  ) : (
                    <span className={styles.statusPending}>Preparing...</span>
                  )}
                </div>
                <p>{menu.lunch}</p>
                {!menu.isLunchReady && (
                  <button className={styles.notifyBtn}>
                    <Bell size={16} /> Mark Ready & Notify
                  </button>
                )}
              </div>

              <div className={styles.mealSection}>
                <div className={styles.mealHeader}>
                  <h3>Dinner</h3>
                  {menu.isDinnerReady ? (
                    <span className={styles.statusReady}><CheckCircle size={16}/> Ready</span>
                  ) : (
                    <span className={styles.statusPending}>Preparing...</span>
                  )}
                </div>
                <p>{menu.dinner}</p>
                {!menu.isDinnerReady && (
                  <button className={styles.notifyBtn}>
                    <Bell size={16} /> Mark Ready & Notify
                  </button>
                )}
              </div>
            </div>
          </div>
        </div>
      )}

      {activeTab === 'housekeeping' && (
        <div className={styles.tabContent}>
          <div className={styles.card}>
            <div className={styles.cardHeader}>
              <h2>Housekeeping Schedule</h2>
            </div>
            <p>Housekeeping features coming soon...</p>
          </div>
        </div>
      )}
    </div>
  );
}

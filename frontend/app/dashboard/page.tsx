"use client";

import React, { useState } from 'react';
import { Plus } from 'lucide-react';
import styles from './dashboard.module.css';

export default function DashboardPage() {
  const [properties, setProperties] = useState([
    { id: 1, name: 'Sunrise PG', address: 'Koramangala, Bangalore', beds: 45, occupied: 40 },
    { id: 2, name: 'Elite Living', address: 'HSR Layout, Bangalore', beds: 60, occupied: 35 },
  ]);

  return (
    <div className={styles.pageContainer}>
      <div className={styles.pageHeader}>
        <div>
          <h1>Welcome back, John!</h1>
          <p>Here's an overview of your PG properties.</p>
        </div>
        <button className={styles.primaryBtn}>
          <Plus size={20} />
          Add New PG
        </button>
      </div>

      <div className={styles.statsGrid}>
        <div className={styles.statCard}>
          <h3>Total Properties</h3>
          <p className={styles.statValue}>{properties.length}</p>
        </div>
        <div className={styles.statCard}>
          <h3>Total Beds</h3>
          <p className={styles.statValue}>
            {properties.reduce((acc, curr) => acc + curr.beds, 0)}
          </p>
        </div>
        <div className={styles.statCard}>
          <h3>Occupied Beds</h3>
          <p className={styles.statValue}>
            {properties.reduce((acc, curr) => acc + curr.occupied, 0)}
          </p>
        </div>
        <div className={styles.statCard}>
          <h3>Occupancy Rate</h3>
          <p className={styles.statValue}>
            {Math.round(
              (properties.reduce((acc, curr) => acc + curr.occupied, 0) /
                properties.reduce((acc, curr) => acc + curr.beds, 0)) *
                100
            )}%
          </p>
        </div>
      </div>

      <div className={styles.section}>
        <div className={styles.sectionHeader}>
          <h2>Your Properties</h2>
        </div>
        
        <div className={styles.propertyGrid}>
          {properties.map((prop) => (
            <div key={prop.id} className={styles.propertyCard}>
              <div className={styles.propertyInfo}>
                <h3>{prop.name}</h3>
                <p>{prop.address}</p>
              </div>
              
              <div className={styles.propertyStats}>
                <div className={styles.pStat}>
                  <span>Total Beds</span>
                  <strong>{prop.beds}</strong>
                </div>
                <div className={styles.pStat}>
                  <span>Available</span>
                  <strong className={styles.successText}>{prop.beds - prop.occupied}</strong>
                </div>
              </div>
              
              <div className={styles.progressBar}>
                <div 
                  className={styles.progressFill} 
                  style={{ width: `${(prop.occupied / prop.beds) * 100}%` }}
                ></div>
              </div>
              <div className={styles.propertyFooter}>
                <button className={styles.outlineBtn}>Manage</button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

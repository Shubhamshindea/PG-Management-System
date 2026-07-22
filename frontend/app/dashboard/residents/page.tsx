"use client";

import React, { useState } from 'react';
import { UserPlus, Search, Filter } from 'lucide-react';
import styles from './residents.module.css';

export default function ResidentsPage() {
  const [residents, setResidents] = useState([
    { id: 1, name: 'Alice Smith', pg: 'Sunrise PG', room: '101', bed: 'A', rent: 8000, status: 'Active' },
    { id: 2, name: 'Bob Johnson', pg: 'Elite Living', room: '204', bed: 'B', rent: 10000, status: 'Active' },
  ]);

  return (
    <div className={styles.pageContainer}>
      <div className={styles.pageHeader}>
        <div>
          <h1>Residents</h1>
          <p>Manage your PG residents, bed allocations, and documents.</p>
        </div>
        <button className={styles.primaryBtn}>
          <UserPlus size={20} />
          Add Resident
        </button>
      </div>

      <div className={styles.toolbar}>
        <div className={styles.searchBar}>
          <Search size={18} className={styles.searchIcon} />
          <input type="text" placeholder="Search residents by name or room..." />
        </div>
        <div className={styles.filters}>
          <button className={styles.filterBtn}>
            <Filter size={18} />
            Filter
          </button>
        </div>
      </div>

      <div className={styles.tableContainer}>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Name</th>
              <th>Property</th>
              <th>Room & Bed</th>
              <th>Rent (Monthly)</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {residents.map((res) => (
              <tr key={res.id}>
                <td>
                  <div className={styles.residentName}>
                    <div className={styles.avatar}>{res.name.charAt(0)}</div>
                    <span>{res.name}</span>
                  </div>
                </td>
                <td>{res.pg}</td>
                <td>
                  <span className={styles.badge}>{res.room} - {res.bed}</span>
                </td>
                <td>₹{res.rent}</td>
                <td>
                  <span className={`${styles.statusBadge} ${styles.statusActive}`}>
                    {res.status}
                  </span>
                </td>
                <td>
                  <button className={styles.actionBtn}>View Details</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

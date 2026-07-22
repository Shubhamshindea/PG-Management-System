import './globals.css';
import type { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'PG Connect | The Ultimate PG Management Platform',
  description: 'Manage your PG operations seamlessly with PG Connect.',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}

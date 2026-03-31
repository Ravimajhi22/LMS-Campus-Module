import { Routes, Route, Navigate } from 'react-router-dom'
import MainLayout from './components/Layout/MainLayout'
import Dashboard from './pages/Dashboard'
import HostelManagement from './pages/Hostel'
import TransportManagement from './pages/Transport'
import LibraryManagement from './pages/Library'

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<Dashboard />} />
        <Route path="hostel" element={<HostelManagement />} />
        <Route path="transport" element={<TransportManagement />} />
        <Route path="library" element={<LibraryManagement />} />
        {/* Fallback to Dashboard */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Route>
    </Routes>
  )
}

export default App

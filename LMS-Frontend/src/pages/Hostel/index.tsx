import { useState } from 'react'
import { AnimatePresence } from 'framer-motion'
import { 
  INITIAL_HOSTELS, 
  INITIAL_ROOMS, 
  INITIAL_STUDENTS, 
  INITIAL_ATTENDANCE, 
  INITIAL_COMPLAINTS 
} from './mockData'

// Sub-components
import HostelHeader from './components/HostelHeader'
import HostelStats from './components/HostelStats'
import HostelDataGrid from './components/HostelDataGrid'
import CreationDrawer from './components/CreationDrawer'

// Types
import { 
  Hostel, 
  HostelRoom, 
  StudentHostelAllocation, 
  HostelAttendance, 
  HostelComplaint 
} from '../../types/hostel'

const HostelManagement = () => {
  // State Management
  const [activeTab, setActiveTab] = useState('hostels')
  const [isDrawerOpen, setIsDrawerOpen] = useState(false)
  const [formData, setFormData] = useState<any>({})

  // Registry Data State
  const [hostels, setHostels] = useState<Hostel[]>(INITIAL_HOSTELS)
  const [rooms, setRooms] = useState<HostelRoom[]>(INITIAL_ROOMS)
  const [students, setStudents] = useState<StudentHostelAllocation[]>(INITIAL_STUDENTS)
  const [attendance, setAttendance] = useState<HostelAttendance[]>(INITIAL_ATTENDANCE)
  const [complaints, setComplaints] = useState<HostelComplaint[]>(INITIAL_COMPLAINTS)

  // CRUD Operations
  const handleSave = () => {
    const id = Date.now()
    const timestamp = new Date().toISOString().split('T')[0]

    switch (activeTab) {
      case 'hostels':
        setHostels([...hostels, { ...formData, hostelId: id, status: 'ACTIVE', totalRooms: formData.totalRooms || 0, totalBlocks: formData.totalBlocks || 0 }])
        break
      case 'rooms':
        setRooms([...rooms, { ...formData, roomId: id, currentlyOccupied: 0, status: 'AVAILABLE' }])
        break
      case 'students':
        setStudents([...students, { ...formData, allocationId: id, studentId: id, status: 'ACTIVE', joinDate: timestamp }])
        break
      case 'attendance':
        setAttendance([...attendance, { ...formData, attendanceId: id, attendanceDate: timestamp, markedAt: new Date().toISOString() }])
        break
      case 'complaints':
        setComplaints([...complaints, { ...formData, complaintId: id, reportedDate: timestamp, status: 'OPEN' }])
        break
    }
    
    setIsDrawerOpen(false)
    setFormData({})
  }

  return (
    <div className="space-y-12 pb-24 relative min-h-screen">
      {/* Header & Navigation */}
      <HostelHeader 
        activeTab={activeTab} 
        setActiveTab={setActiveTab} 
        setIsDrawerOpen={setIsDrawerOpen} 
      />

      {/* Main Data Matrix */}
      <AnimatePresence mode="wait">
        <HostelDataGrid 
          activeTab={activeTab}
          hostels={hostels}
          rooms={rooms}
          students={students}
          attendance={attendance}
          complaints={complaints}
        />
      </AnimatePresence>

      {/* Dynamic Status Grid */}
      <HostelStats />

      {/* Record Creation Interface */}
      <CreationDrawer 
        isOpen={isDrawerOpen}
        onClose={() => setIsDrawerOpen(false)}
        activeTab={activeTab}
        formData={formData}
        setFormData={setFormData}
        onSave={handleSave}
        hostels={hostels}
      />
    </div>
  )
}

export default HostelManagement

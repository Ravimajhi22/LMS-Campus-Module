import { motion, AnimatePresence } from 'framer-motion'
import { Plus } from 'lucide-react'
import { Hostel } from '../../../types/hostel'

interface CreationDrawerProps {
  isOpen: boolean
  onClose: () => void
  activeTab: string
  formData: any
  setFormData: (data: any) => void
  onSave: () => void
  hostels: Hostel[]
}

const CreationDrawer = ({ isOpen, onClose, activeTab, formData, setFormData, onSave, hostels }: CreationDrawerProps) => {
  return (
    <AnimatePresence>
      {isOpen && (
        <>
          <motion.div 
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            onClick={onClose}
            className="fixed inset-0 bg-slate-900/40 backdrop-blur-sm z-[100]"
          />
          <motion.div 
            initial={{ x: '100%' }}
            animate={{ x: 0 }}
            exit={{ x: '100%' }}
            transition={{ type: 'spring', damping: 25, stiffness: 200 }}
            className="fixed right-0 top-0 bottom-0 w-full max-w-xl bg-white border-l border-slate-100 z-[101] shadow-2xl flex flex-col"
          >
            <div className="p-10 border-b border-slate-100 flex items-center justify-between bg-slate-50/50">
              <div>
                 <h2 className="text-2xl font-black text-slate-900 uppercase tracking-tighter italic">Create New {activeTab.slice(0, -1)}</h2>
                 <p className="text-primary-600 text-[10px] font-black uppercase tracking-widest mt-1">Registry Entry Protocol v5.1S</p>
              </div>
              <button onClick={onClose} className="h-11 w-11 rounded-xl bg-white flex items-center justify-center text-slate-400 hover:text-rose-500 transition-all border border-slate-200 shadow-sm">
                 <Plus size={20} className="rotate-45" />
              </button>
            </div>

            <div className="flex-1 overflow-y-auto p-10 space-y-10 custom-scrollbar">
              {activeTab === 'hostels' && (
                <div className="space-y-8">
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Hostel Signature Name</label>
                    <input 
                      onChange={(e) => setFormData({...formData, hostelName: e.target.value})}
                      placeholder="e.g. Sapphire Heights" 
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 placeholder-slate-300 focus:ring-2 focus:ring-primary-600/20 focus:border-primary-600 outline-none transition-all font-bold" 
                    />
                  </div>
                  <div className="grid grid-cols-2 gap-6">
                    <div className="space-y-3">
                      <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Classification Type</label>
                      <select 
                        onChange={(e) => setFormData({...formData, hostelType: e.target.value})}
                        className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none appearance-none cursor-pointer"
                      >
                        <option value="MEN">MEN ONLY</option>
                        <option value="WOMEN">WOMEN ONLY</option>
                        <option value="COLIVING">COLIVING SPACE</option>
                      </select>
                    </div>
                    <div className="space-y-3">
                      <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Total Block Integrity</label>
                      <input 
                        type="number"
                        onChange={(e) => setFormData({...formData, totalBlocks: parseInt(e.target.value)})}
                        placeholder="0" 
                        className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold placeholder-slate-300 focus:ring-2 focus:ring-primary-600/20 outline-none transition-all" 
                      />
                    </div>
                  </div>
                </div>
              )}

              {activeTab === 'rooms' && (
                <div className="space-y-8">
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Room Vector ID</label>
                    <input 
                      onChange={(e) => setFormData({...formData, roomNumber: e.target.value})}
                      placeholder="e.g. RM-101" 
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none" 
                    />
                  </div>
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Parent Entity Link</label>
                    <select 
                      onChange={(e) => setFormData({...formData, hostelName: e.target.value})}
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none appearance-none"
                    >
                      <option value="">Select Target Hostel</option>
                      {hostels.map(h => <option key={h.hostelId} value={h.hostelName}>{h.hostelName}</option>)}
                    </select>
                  </div>
                </div>
              )}

              {activeTab === 'students' && (
                <div className="space-y-8">
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">User Identity Profile</label>
                    <input 
                      onChange={(e) => setFormData({...formData, studentName: e.target.value})}
                      placeholder="Legal Full Name" 
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none" 
                    />
                  </div>
                  <div className="grid grid-cols-2 gap-6">
                    <div className="space-y-3">
                      <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Guardian Protocol Name</label>
                      <input 
                        onChange={(e) => setFormData({...formData, parentName: e.target.value})}
                        placeholder="Primary Contact" 
                        className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none" 
                      />
                    </div>
                    <div className="space-y-3">
                      <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Emergency Signal Phone</label>
                      <input 
                        onChange={(e) => setFormData({...formData, parentPhone: e.target.value})}
                        placeholder="+91..." 
                        className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none font-mono" 
                      />
                    </div>
                  </div>
                </div>
              )}

              {activeTab === 'attendance' && (
                <div className="space-y-8">
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Student Registry Lookup</label>
                    <input 
                      onChange={(e) => setFormData({...formData, studentName: e.target.value})}
                      placeholder="Search active profiles..." 
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none" 
                    />
                  </div>
                </div>
              )}

              {activeTab === 'complaints' && (
                <div className="space-y-8">
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">Issue Logic Category</label>
                    <select 
                      onChange={(e) => setFormData({...formData, issueCategory: e.target.value})}
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none appearance-none cursor-pointer"
                    >
                      <option value="PLUMBING">PLUMBING LOG</option>
                      <option value="ELECTRICAL">ELECTRICAL LOG</option>
                      <option value="CLEANING">MAINTENANCE</option>
                      <option value="INTERNET">NETWORK DIAGNOSTICS</option>
                    </select>
                  </div>
                  <div className="space-y-3">
                    <label className="text-[10px] font-black text-slate-400 uppercase tracking-widest ml-1">String Description Content</label>
                    <textarea 
                      onChange={(e) => setFormData({...formData, description: e.target.value})}
                      rows={4}
                      placeholder="Input detailed issue diagnostics..." 
                      className="w-full bg-slate-50 border border-slate-200 rounded-2xl px-6 py-4 text-slate-900 font-bold focus:ring-2 focus:ring-primary-600/20 outline-none resize-none" 
                    />
                  </div>
                </div>
              )}
            </div>

            <div className="p-10 border-t border-slate-100 bg-slate-50/80 flex gap-4">
              <button 
                onClick={onClose}
                className="flex-1 py-4 rounded-2xl border border-slate-200 text-[10px] font-black text-slate-500 uppercase tracking-widest hover:text-slate-900 hover:bg-white transition-all shadow-sm"
              >
                Cancel Entry
              </button>
              <button 
                onClick={onSave}
                className="flex-[2] py-4 rounded-2xl bg-primary-600 text-white text-[10px] font-black uppercase tracking-widest hover:bg-primary-700 shadow-xl shadow-primary-600/20 transition-all active:scale-95"
              >
                Commit to Registry
              </button>
            </div>
          </motion.div>
        </>
      )}
    </AnimatePresence>
  )
}

export default CreationDrawer

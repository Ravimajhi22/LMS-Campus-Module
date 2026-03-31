import { Search, Plus, Filter, LayoutGrid, List, BookOpen, Star, Zap, Archive, Eye, Share2, HardDrive, ShieldAlert, Download } from 'lucide-react'
import { motion } from 'framer-motion'

const LibraryManagement = () => {
  const books = [
    { title: 'Advanced_Java_V4', author: 'Kathy Sierra', isbn: '978-0596009205', category: 'Computing', available: 12, total: 15, rating: 4.8 },
    { title: 'The_Pragmatic_Env', author: 'Andrew Hunt', isbn: '978-0201616224', category: 'Software Eng', available: 4, total: 20, rating: 4.9 },
    { title: 'Clean_Code_Base', author: 'Robert C. Martin', isbn: '978-0132350884', category: 'Architecture', available: 0, total: 8, rating: 4.7 },
    { title: 'Spring_Boot_Core', author: 'Craig Walls', isbn: '978-1617291203', category: 'Frameworks', available: 7, total: 10, rating: 4.5 },
  ]

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: { opacity: 1, transition: { staggerChildren: 0.1 } }
  }

  const itemVariants = {
    hidden: { y: 20, opacity: 0 },
    visible: { y: 0, opacity: 1 }
  }

  return (
    <motion.div variants={containerVariants} initial="hidden" animate="visible" className="space-y-12 pb-24">
      {/* Archive Header */}
      <div className="flex flex-col xl:flex-row xl:items-end justify-between gap-10 border-b border-white/[0.03] pb-12">
        <div className="max-w-2xl">
          <motion.div 
            initial={{ y: 20, opacity: 0 }}
            animate={{ y: 0, opacity: 1 }}
            className="flex items-center gap-4 mb-6"
          >
            <div className="h-12 w-12 rounded-2xl bg-primary-500/10 flex items-center justify-center text-primary-400 border border-primary-500/20 shadow-2xl">
               <Archive size={24} />
            </div>
            <h1 className="text-4xl font-black text-white tracking-tighter uppercase tracking-[-0.04em]">Library Management</h1>
          </motion.div>
          <p className="text-slate-500 text-lg font-medium leading-relaxed">
            Manage books, digital resources and library inventory.
          </p>
        </div>
        <div className="flex flex-wrap gap-4">
          <button className="flex items-center gap-4 bg-slate-950/40 border border-white/[0.04] text-white px-8 py-4 rounded-[1.5rem] font-black text-xs uppercase tracking-widest hover:bg-slate-950/60 transition-all shadow-xl">
            <Plus size={18} strokeWidth={3} />
            Add Book
          </button>
          <button className="flex items-center gap-4 bg-white text-slate-950 px-10 py-5 rounded-[1.5rem] font-black text-xs uppercase tracking-[0.2em] hover:bg-primary-50 active:scale-95 transition-all shadow-2xl">
            <Zap size={20} fill="currentColor" />
            Issue Portal
          </button>
        </div>
      </div>

      {/* Global Filter Bar */}
      <div className="glass-ultra p-3 flex flex-col md:flex-row items-center gap-4 rounded-[2rem] border border-white/[0.03] shadow-2xl">
        <div className="flex-1 flex items-center bg-slate-950/40 border border-white/[0.04] rounded-2xl px-6 py-4 text-slate-400 focus-within:ring-2 focus-within:ring-primary-500/20 transition-all group">
          <Search size={20} className="group-focus-within:text-primary-400 transition-colors" />
          <input 
            type="text" 
            placeholder="Search resources by Title_ID, Author_Base, or Global Protocol..." 
            className="bg-transparent border-none outline-none text-sm w-full ml-4 text-slate-300 placeholder-slate-700 font-bold uppercase tracking-tight" 
          />
        </div>
        
        <div className="flex items-center gap-3">
          <button className="p-4 bg-slate-950/40 border border-white/[0.04] rounded-2xl text-slate-600 hover:text-white hover:bg-slate-950/60 transition-all">
            <Filter size={20} />
          </button>
          <button className="p-4 bg-slate-950/40 border border-white/[0.04] rounded-2xl text-slate-600 hover:text-white hover:bg-slate-950/60 transition-all">
            <HardDrive size={20} />
          </button>
          <div className="h-10 w-[1px] bg-white/[0.04] mx-2"></div>
          <div className="flex bg-slate-950/60 border border-white/[0.04] rounded-2xl p-2 gap-2 shadow-inner">
            <button className="p-2.5 bg-primary-500 text-white rounded-xl shadow-2xl transition-all"><LayoutGrid size={22} /></button>
            <button className="p-2.5 text-slate-700 hover:text-slate-300 transition-all"><List size={22} /></button>
          </div>
        </div>
      </div>

      {/* Resource Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-10">
        {books.map((book, idx) => (
          <motion.div key={idx} variants={itemVariants} className="glass-v2 group relative overflow-hidden transition-all border border-white/[0.03] rounded-[2.5rem]">
             <div className="absolute top-0 right-0 p-8 opacity-0 group-hover:opacity-100 transition-all duration-500 scale-90 group-hover:scale-100 flex gap-2">
               <button className="h-10 w-10 bg-slate-950/80 rounded-xl flex items-center justify-center text-slate-500 hover:text-white border border-white/5"><Eye size={18} /></button>
               <button className="h-10 w-10 bg-slate-950/80 rounded-xl flex items-center justify-center text-slate-500 hover:text-rose-400 border border-white/5"><Share2 size={18} /></button>
             </div>
             
             <div className="p-10">
                <div className={`h-16 w-16 rounded-[1.5rem] bg-slate-950 flex items-center justify-center mb-10 transition-all duration-700 group-hover:scale-125 group-hover:rotate-12 border border-white/[0.02] shadow-2xl ${
                  book.available > 0 ? 'text-primary-400' : 'text-rose-500'
               }`}>
                  <BookOpen size={32} strokeWidth={1} />
               </div>

               <div className="mb-10">
                 <h3 className="text-2xl font-black text-white group-hover:text-wow transition-all duration-500 mb-2 leading-tight uppercase tracking-tight min-h-[3.5rem]">{book.title}</h3>
                 <span className="text-[10px] uppercase tracking-[0.4em] font-black text-slate-700 block mb-4">{book.author}</span>
                 
                 <div className="flex items-center gap-3">
                    <div className="flex items-center gap-1.5 px-2 py-1 bg-white/[0.02] border border-white/[0.04] rounded-lg">
                       <Star size={10} className="text-amber-500 fill-amber-500" />
                       <span className="text-[10px] font-black text-slate-400">{book.rating}</span>
                    </div>
                    <div className="h-1 w-1 bg-slate-800 rounded-full" />
                    <span className="text-[10px] font-mono font-black text-slate-700 truncate">{book.isbn}</span>
                 </div>
               </div>

               <div className="mt-10 space-y-5 mb-10">
                  <div className="flex justify-between items-center px-1">
                     <span className="text-[9px] text-slate-700 uppercase tracking-widest font-black">Archive Integrity</span>
                     <span className={`text-[10px] font-black tracking-tight ${book.available > 0 ? 'text-emerald-400' : 'text-rose-400'}`}>
                        NODES: {book.available} / {book.total}
                     </span>
                  </div>
                  <div className="h-2 w-full bg-slate-950/60 rounded-full border border-white/[0.02] overflow-hidden p-[3px]">
                     <div 
                        className={`h-full rounded-full transition-all duration-1000 shadow-lg ${book.available > 0 ? 'bg-gradient-to-r from-primary-500 to-sky-400 shadow-primary-500/20' : 'bg-rose-500 shadow-rose-500/20'}`} 
                        style={{ width: `${(book.available / book.total) * 100}%` }}
                      />
                  </div>
               </div>

               <div className="pt-8 border-t border-white/[0.03] flex justify-between items-center relative overflow-hidden">
                  <div className="flex items-center gap-3">
                      <div className="px-3 py-1.5 bg-primary-500/10 border border-primary-500/20 rounded-xl">
                        <span className="text-[9px] font-black text-primary-400 uppercase tracking-widest">{book.category}</span>
                     </div>
                  </div>
                  
                  <button className={`flex items-center gap-3 px-6 py-2.5 rounded-xl text-[10px] font-black uppercase tracking-widest transition-all ${
                    book.available > 0 
                    ? 'bg-white/5 text-primary-400 hover:bg-white/10 hover:text-white border border-white/5' 
                    : 'text-slate-800 pointer-events-none opacity-40'
                  }`}>
                    {book.available > 0 ? <Zap size={14} fill="currentColor" /> : <ShieldAlert size={14} />}
                    {book.available > 0 ? 'Sync' : 'Locked'}
                  </button>
               </div>
             </div>
             
             {/* Decorative Corner Glow */}
             <div className="absolute -bottom-8 -right-8 h-24 w-24 bg-white/5 rounded-full blur-2xl opacity-0 group-hover:opacity-100 transition-opacity" />
          </motion.div>
        ))}
      </div>

      {/* Global Information Footer Hub */}
      <motion.div variants={itemVariants} className="glass-ultra rounded-[2.5rem] border border-white/[0.02] overflow-hidden divide-y divide-white/[0.04] shadow-2xl">
         <div className="p-10 flex flex-col lg:flex-row items-center justify-between gap-8">
            <div className="flex items-center gap-6">
                <div className="h-16 w-16 bg-slate-950 border border-white/[0.04] rounded-2xl flex items-center justify-center text-primary-400">
                  <Download size={24} strokeWidth={1.5} />
               </div>
               <div>
                  <h4 className="text-2xl font-black text-white uppercase tracking-tighter">Terminal Manifest.PDF</h4>
                  <p className="text-slate-600 text-xs font-black uppercase tracking-widest mt-1">Export Global Resource Metadata [2.4MB]</p>
               </div>
            </div>
            <button className="px-10 py-4 bg-primary-500 text-white rounded-2xl text-[10px] font-black uppercase tracking-[0.3em] hover:bg-primary-400 shadow-2xl transition-all">Download Archives</button>
         </div>
      </motion.div>
    </motion.div>
  )
}

export default LibraryManagement

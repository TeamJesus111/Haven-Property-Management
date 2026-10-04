package com.example.mobileappproj
// --- CONSTANTS & DATA MODELS ---
const val APP_NAME = "NEON HAVEN // RENTAL PORTAL"

data class Property(
    val id: Int,
    val title: String,
    val location: String,
    val priceKsh: Double,
    val bedrooms: Int,
    var isAvailable: Boolean = true
)

data class Booking(
    val propertyTitle: String,
    val clientName: String
)

// --- ANSI NEON COLOR CODES ---
const val RESET = "\u001B[0m"
const val CYAN = "\u001B[36m"
const val PURPLE = "\u001B[35m"
const val PINK = "\u001B[95m"
const val BOLD = "\u001B[1m"

fun main() {
    val properties = mutableListOf(
        Property(1, "Apex Cyber Heights", "Nairobi CBD", 65000.0, 2),
        Property(2, "Neon Oasis Villa", "Westlands", 120000.0, 4),
        Property(3, "Pulse Studio Lofts", "Kileleshwa", 42000.0, 1),
        Property(4, "Vaporwave Residency", "Kilimani", 85000.0, 3)
    )
    val bookings = mutableListOf()

    var running = true

    while (running) {
        println("\n\(CYAN\)BOLD=============================================$RESET")
        println("\(PINK\)BOLD      \(APP_NAME\)RESET")
        println("\(CYAN\)BOLD=============================================$RESET")
        println("\(PURPLE[1]\)RESET View All Properties")
        println("\(PURPLE[2]\)RESET Search Property (Location & Budget)")
        println("\(PURPLE[3]\)RESET Add New Listing (Landlord/Agent)")
        println("\(PURPLE[4]\)RESET Book a Viewing")
        println("\(PURPLE[5]\)RESET View Bookings")
        println("\(PURPLE[6]\)RESET Exit")
        print("\(PINK>> Select option:\)RESET")

        when (readlnOrNull()?.trim()) {
            "1" -> displayProperties(properties)
            "2" -> searchProperties(properties)
            "3" -> addProperty(properties)
            "4" -> bookViewing(properties, bookings)
            "5" -> displayBookings(bookings)
            "6" -> {
                println("\n\(PINK Exiting...\)RESET")
                running = false
            }
            else -> println("\n\(PINK[!] Invalid selection. Try again.\)RESET")
        }
    }
}

fun displayProperties(properties: List) {
    println("\n\(CYAN--- AVAILABLE PROPERTIES ---\)RESET")
    for (p in properties) {
        val status = if (p.isAvailable) "\(CYAN[AVAILABLE]\)RESET" else "\(PINK[OCCUPIED]\)RESET"
        println("\({p.id}.\){p.title} | Location: \({p.location} | KSh\){p.priceKsh} | \({p.bedrooms} Beds |\)status")
    }
}

fun searchProperties(properties: List) {
    print("\nEnter location target: ")
    val loc = readlnOrNull() ?: ""
    print("Enter maximum budget (KSh): ")
    val maxBudget = readlnOrNull()?.toDoubleOrNull() ?: Double.MAX_VALUE

    println("\n\(CYAN--- SEARCH RESULTS ---\)RESET")
    val results = properties.filter {
        it.location.contains(loc, ignoreCase = true) && it.priceKsh <= maxBudget
    }

    if (results.isEmpty()) {
        println("\(PINK No properties found matching criteria.\)RESET")
    } else {
        results.forEach { println("\({it.id}.\){it.title} in \({it.location} - KSh\){it.priceKsh}") }
    }
}

fun addProperty(properties: MutableList) {
    println("\n\(CYAN--- ADD NEW LISTING ---\)RESET")
    print("Enter Property Title: ")
    val title = readlnOrNull() ?: ""
    print("Enter Location: ")
    val location = readlnOrNull() ?: ""
    print("Enter Price (KSh): ")
    val price = readlnOrNull()?.toDoubleOrNull() ?: 0.0
    print("Enter Bedrooms: ")
    val beds = readlnOrNull()?.toIntOrNull() ?: 1

    properties.add(Property(properties.size + 1, title, location, price, beds))
    println("\(PINK[✓] Listing added successfully!\)RESET")
}

fun bookViewing(properties: List, bookings: MutableList) {
    println("\n\(CYAN--- BOOK A VIEWING ---\)RESET")
    displayProperties(properties)
    print("\nEnter Property ID to book: ")
    val id = readlnOrNull()?.toIntOrNull()

    val prop = properties.find { it.id == id }

    if (prop != null && prop.isAvailable) {
        print("Enter your name: ")
        val name = readlnOrNull() ?: "Client"
        bookings.add(Booking(prop.title, name))
        prop.isAvailable = false
        println("\(PINK[✓] Viewing successfully booked for\)name!$RESET")
    } else {
        println("\(PINK[!] Property unavailable or invalid ID.\)RESET")
    }
}

fun displayBookings(bookings: List) {
    println("\n\(CYAN--- YOUR BOOKED VIEWINGS ---\)RESET")
    if (bookings.isEmpty()) {
        println("No viewing appointments yet.")
    } else {
        bookings.forEachIndexed { i, b ->
            println("\({i + 1}. Client:\){b.clientName} -> Reserved: ${b.propertyTitle}")
        }
    }
}